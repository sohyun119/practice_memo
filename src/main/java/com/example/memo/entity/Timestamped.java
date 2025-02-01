package com.example.memo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity Class들이 해당 클래스를 상속받을 경우
                    // 아래의 추상클래스들에 선언한 멤버변수를 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class) // Auditing 기능을 추가 -> 자동으로 시간을 넣어주는 기능
public abstract class Timestamped {

    @CreatedDate
    @Column(updatable = false) // update막하둠
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;
}