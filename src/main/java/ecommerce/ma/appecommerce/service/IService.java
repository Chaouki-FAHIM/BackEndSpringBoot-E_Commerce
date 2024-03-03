package ecommerce.ma.appecommerce.service;

import java.util.List;

public interface IService<RequestDTO,ResponseDTO,ID> {
    List<ResponseDTO> fetchAll();
    ResponseDTO searcheByID(ID id);
    ResponseDTO add(RequestDTO requestDTO);
    ResponseDTO update(RequestDTO requestDTO,ID id);
    ResponseDTO delete(ID id);

}
