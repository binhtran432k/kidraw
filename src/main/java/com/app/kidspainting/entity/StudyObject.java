package com.app.kidspainting.entity;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class StudyObject {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "art_type_id", nullable = false)
    private ArtType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_level_id", nullable = false)
    private ArtLevel level;
}
