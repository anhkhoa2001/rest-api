package org.example.postgres.service.converter;

import org.example.dto.BorrowBookDTO;
import org.example.postgres.model.BorrowBook;
import org.springframework.stereotype.Component;

@Component
public class BorrowBookConverter {

    public BorrowBookDTO convertModel2DTO(BorrowBook source) {
        if(source == null) {
            return null;
        }
        BorrowBookDTO target = new BorrowBookDTO();
        target.setBook_id(source.getBook_id());
        target.setBorrow_date(source.getBorrow_date());
        if(source.getId() != null) {
            target.setId(source.getId());
        }
        target.setCustomer_id(source.getCustomer_id());
        target.setType_id(source.getType_id());
        target.setFirst_character(source.getFirst_character());
        target.setAuthor_id(source.getAuthor_id());

        return target;
    }

    public BorrowBook convertDTO2Model(BorrowBookDTO source) {
        if(source == null) {
            return null;
        }
        BorrowBook target = new BorrowBook();
        target.setBook_id(source.getBook_id());
        target.setBorrow_date(source.getBorrow_date());
        target.setCustomer_id(source.getCustomer_id());
        target.setType_id(source.getType_id());
        target.setFirst_character(source.getFirst_character());
        target.setAuthor_id(source.getAuthor_id());

        return target;
    }
}
