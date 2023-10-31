package az.spring.bookstore.mapper;

import az.spring.bookstore.domain.User;
import az.spring.bookstore.request.UserSignupRequest;
import az.spring.bookstore.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User fromUserSignUpRequestToModel(UserSignupRequest userSignupRequest);


    UserResponse fromModelToResponse(User user);

}