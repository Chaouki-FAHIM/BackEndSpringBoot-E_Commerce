package ecommerce.ma.appecommerce.service;

import ecommerce.ma.appecommerce.exception.NotFoundException;
import ecommerce.ma.appecommerce.exception.NotValidDataException;
import ecommerce.ma.appecommerce.exception.RequiredDataException;

import java.util.List;

public interface IService<RequestDTO,ResponseDTO,ID> {
    List<ResponseDTO> fetchAll();
    ResponseDTO searcheByID(ID id) throws NotFoundException;
    ResponseDTO add(RequestDTO requestDTO) throws RequiredDataException, NotValidDataException;
    ResponseDTO update(RequestDTO requestDTO,ID id) throws NotFoundException, RequiredDataException, NotValidDataException;
    void delete(ID id) throws NotFoundException;

}
