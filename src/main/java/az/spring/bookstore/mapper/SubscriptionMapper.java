package az.spring.bookstore.mapper;

import az.spring.bookstore.domain.Subscription;
import az.spring.bookstore.response.SubscriptionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SubscriptionMapper {

    SubscriptionResponse fromModelToResponse(Subscription subscription);

}