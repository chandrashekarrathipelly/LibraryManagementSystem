package com.example.Library.DTO;

import java.util.List;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    long student_id;
    public List<Long> book_id_List;

}
