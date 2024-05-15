package com.arthurlamberti.videoplataform.application.video.retrieve.list;

import com.arthurlamberti.videoplataform.application.UseCase;
import com.arthurlamberti.videoplataform.domain.pagination.Pagination;
import com.arthurlamberti.videoplataform.domain.video.VideoSearchQuery;

public abstract class ListVideosUseCase extends UseCase<VideoSearchQuery, Pagination<VideoListOutput>> {
}
