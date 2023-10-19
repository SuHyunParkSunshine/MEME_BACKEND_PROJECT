package project.meme.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class) // 해당 클래스에 Auditing 기능(데이터 생성, 수정, 삭제될 때 변경사항 기록, 추적)을 포함
@MappedSuperclass //JPA Entity 클래스들이 해당 추상 클래스를 상속할 경우 추상클래스의 필드를 컬럼으로 인식
public abstract class AuditingFields {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) // 날짜, 시간 필드 문자열 변환 or 패턴 정의
    @CreatedDate // Entity가 생성되어 저장될 때 시간이 자동 저장
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    @CreatedBy // Entity가 생성될 때 자동으로 작성자 정보 저장
    @Column(nullable = false, updatable = false, length = 100)
    @Setter
    protected String createdBy;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate // 조회한 Entity의 값을 변경할 떄 시간이 자동 저장
    @Column(nullable = false)
    protected LocalDateTime modifiedAt;

    @LastModifiedBy // Entity가 갱신될 때 자동으로 마지막 수정자 정보를 저장
    @Column(nullable = false, length = 100)
    @Setter
    protected String modifiedBy;
}
