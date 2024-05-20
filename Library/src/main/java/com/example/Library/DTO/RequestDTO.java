package com.example.Library.DTO;

import java.util.List;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {
    public List<Long> book_id_List;
}
