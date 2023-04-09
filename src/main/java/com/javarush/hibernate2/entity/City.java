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
@Table(name = "city", schema = "movie")
@EntityListeners(value = LastUpdateListener.class)
public class City extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    private String city;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
