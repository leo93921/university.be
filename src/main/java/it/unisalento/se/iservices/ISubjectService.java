package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.SubjectNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.SubjectModel;

public interface ISubjectService {

    SubjectModel getSubjectByID(Integer ID) throws UserTypeNotSupported, SubjectNotFoundException;

    SubjectModel saveSubject(SubjectModel model) throws UserTypeNotSupported;

}
