package com.arthurlamberti.videoplataform.infrastructure.configuration;


import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.EMPTY_LIST;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@Profile("!development")
public class SecurityConfig {

    public static final String CATALOGO_ADMIN = "CATALOGO_ADMIN";
    public static final String CATALOGO_CAST_MEMBERS = "CATALOGO_CAST_MEMBERS";
    public static final String CATALOGO_CATEGORIES = "CATALOGO_CATEGORIES";
    public static final String CATALOGO_GENRES = "CATALOGO_GENRES";
    public static final String CATALOGO_VIDEOS = "CATALOGO_VIDEOS";

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) //desabilita o filtro de csrf nos POST
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .antMatchers("/cast_members*").hasAnyRole(CATALOGO_ADMIN, CATALOGO_CAST_MEMBERS)
                            .antMatchers("/categories*").hasAnyRole(CATALOGO_ADMIN, CATALOGO_CATEGORIES)
                            .antMatchers("/genres*").hasAnyRole(CATALOGO_ADMIN, CATALOGO_GENRES)
                            .antMatchers("/videos*").hasAnyRole(CATALOGO_ADMIN, CATALOGO_VIDEOS)
                            .anyRequest().hasRole(CATALOGO_ADMIN);
                })
                .oauth2ResourceServer(oauth -> {
                    oauth.jwt()
                            .jwtAuthenticationConverter(new KeycloakJwtConverter());
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.frameOptions().sameOrigin())
                .build();
    }

    static class KeycloakJwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

        private final KeycloakAuthoritiesConverter authoritiesConverter;

        public KeycloakJwtConverter() {
            this.authoritiesConverter = new KeycloakAuthoritiesConverter();
        }

        @Override
        public AbstractAuthenticationToken convert(Jwt source) {
            return new JwtAuthenticationToken(source, extractAuthorities(source), extractPrincipal(source));
        }

        private String extractPrincipal(Jwt source) {
            return source.getClaimAsString(JwtClaimNames.SUB);
        }

        private Collection<? extends GrantedAuthority> extractAuthorities(Jwt source) {
            return this.authoritiesConverter.convert(source);
        }
    }

    static class KeycloakAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

        public static final String RESOURCE_ACCESS = "resource_access";
        public static final String REALM_ACCESS = "realm_access";
        public static final String ROLES = "roles";
        public static final String ROLE_PREFIX = "ROLE_";

        @Override
        public Collection<GrantedAuthority> convert(Jwt source) {
            final var realmRoles = extractRealmRoles(source);
            final var resourceRoles = extractResourceRoles(source);
            return Stream.concat(realmRoles, resourceRoles)
                    .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX.concat(role.toUpperCase())))
                    .collect(Collectors.toSet());
        }

        private Stream<String> extractResourceRoles(Jwt source) {

            final Function<Map.Entry<String, Object>, Stream<String>> mapResource = resource -> {
                final var key = resource.getKey();
                final var value =  (JSONObject) resource.getValue();
                final var roles = (Collection<String>) value.get(ROLES);
                return  roles.stream()
                        .map(role -> key.concat("_").concat(role));
            };

            Function<Set<Map.Entry<String, Object>>, Collection<String>> mapResources =
                    resources -> resources.stream()
                            .flatMap(mapResource)
                            .toList();

            return Optional.ofNullable(source.getClaimAsMap(RESOURCE_ACCESS))
                    .map(res -> res.entrySet())
                    .map(mapResources)
                    .orElse(Collections.emptyList())
                    .stream();
        }

        private Stream<String> extractRealmRoles(Jwt source) {
            return Optional.ofNullable(source.getClaimAsMap(REALM_ACCESS))
                    .map(res -> (Collection<String>) res.get(ROLES))
                    .orElse(Collections.emptyList())
                    .stream();
        }
    }

}
