package com.omer.Payment.dto.converter;

import com.omer.Payment.dto.Commerce_CompanyDto;
import com.omer.Payment.model.CommerceCompany;
import org.springframework.stereotype.Component;

@Component
public class CommerceCompanyDtoConverter {

        public Commerce_CompanyDto convert(CommerceCompany from)
        {
            return new Commerce_CompanyDto(
                    from.getId(),
                    from.getCompanyName(),
                    from.getCompanyCode()
            );
        }
}
