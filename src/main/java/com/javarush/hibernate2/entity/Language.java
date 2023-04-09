package com.javarush.hibernate2.entity;

import com.javarush.hibernate2.listener.LastUpdateListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(name = "language", schema = "movie")
@EntityListeners(value = LastUpdateListener.class)
public class Language extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;

    @Column(columnDefinition = "char")
    private String name;
}
