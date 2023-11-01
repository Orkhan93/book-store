package az.spring.bookstore.mapper;

import az.spring.bookstore.domain.Author;
import az.spring.bookstore.domain.Student;
import az.spring.bookstore.request.AuthorRequest;
import az.spring.bookstore.request.StudentRequest;
import az.spring.bookstore.response.AuthorResponse;
import az.spring.bookstore.response.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorMapper {

    Author fromRequestToModel(AuthorRequest authorRequest);

    AuthorResponse fromModelToResponse(Author author);

    AuthorResponse fromRequestToResponse(AuthorRequest authorRequest);

}