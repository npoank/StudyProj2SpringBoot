package com.andrew.StudyProj2SpringBoot.util;




import com.andrew.StudyProj2SpringBoot.models.Person;
import com.andrew.StudyProj2SpringBoot.services.PeopleService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (peopleService.findByFullName(person.getFullName()).isPresent()){
            errors.rejectValue("fullName", "", "This name already exist");
        }

    }
}
