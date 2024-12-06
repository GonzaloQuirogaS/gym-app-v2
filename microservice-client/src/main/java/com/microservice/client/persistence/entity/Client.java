package com.microservice.client.persistence.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String name;
    @NotBlank
    @Size(min = 3)
    private String surname;
    @NotNull
    private Integer age;
    @NotBlank
    private String email;
    @NotNull
    private Long phone;
    @NotNull
    private LocalDateTime registerDate = LocalDateTime.now();

    @Column(name = "activity_id")
    private Long activityId;


}
