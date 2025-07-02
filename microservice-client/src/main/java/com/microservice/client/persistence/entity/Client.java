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
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private Long phone;
    private LocalDateTime activityRegisterDate;
    private LocalDateTime activityExpireDate;
    private LocalDateTime registerDate = LocalDateTime.now();
    @Column(name = "activity_id")
    private Long activityId;
}
