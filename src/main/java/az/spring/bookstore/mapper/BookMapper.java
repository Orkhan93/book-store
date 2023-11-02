package az.spring.bookstore.mapper;

import az.spring.bookstore.domain.Book;
import az.spring.bookstore.request.BookRequest;
import az.spring.bookstore.response.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    Book fromRequestToModel(BookRequest bookRequest);

    BookResponse fromRequestToResponse(BookRequest bookRequest);

    BookResponse fromModelToResponse(Book book);

}