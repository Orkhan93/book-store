package az.spring.bookstore.mapper;

import az.spring.bookstore.domain.Student;
import az.spring.bookstore.request.StudentRequest;
import az.spring.bookstore.response.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {

    Student fromRequestToModel(StudentRequest studentRequest);

    StudentResponse fromModelToResponse(Student student);

    StudentResponse fromRequestToResponse(StudentRequest studentRequest);

}