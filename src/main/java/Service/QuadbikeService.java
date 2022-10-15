package Service;

import Model.Quadbike;
import Repository.QuadbikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuadbikeService {
    @Autowired
    private QuadbikeRepository quadbikeRepository;

    public List<Quadbike> getAll(){
        return (List<Quadbike>) quadbikeRepository.getAll();
    }

    public Optional<Quadbike> getQuadbike(int id){

        return quadbikeRepository.getQuadbike(id);
    }

    public Quadbike save(Quadbike quadbike){
        if(quadbike.getId()== null){
            return quadbikeRepository.save(quadbike);
        }else{
            Optional<Quadbike> quadbikeFound = quadbikeRepository.getQuadbike(quadbike.getId());
            if(quadbikeFound.isEmpty()){
                return quadbikeRepository.save(quadbike);
            }else{
                return quadbike;
            }
        }
    }

    public Quadbike update(Quadbike quadbike){
        if(quadbike.getId()!= null){
            Optional<Quadbike> quadbikeFound = quadbikeRepository.getQuadbike(quadbike.getId());
            if(!quadbikeFound.isEmpty()){
                if(quadbike.getName() != null){
                    quadbikeFound.get().setName(quadbike.getName());
                }
                if(quadbike.getBrand() != null){
                    quadbikeFound.get().setBrand(quadbike.getBrand());
                }
                if(quadbike.getYear() != null){
                    quadbikeFound.get().setYear(quadbike.getYear());
                }
                if(quadbike.getDescription() != null){
                    quadbikeFound.get().setDescription(quadbike.getDescription());
                }
                return quadbikeRepository.save(quadbikeFound.get());
            }
        }
        return quadbike;
    }

    public boolean deleteQuadbike(int quadbikeId) {
        Boolean result = getQuadbike(quadbikeId).map(quadbikeToDelete -> {
            quadbikeRepository.delete(quadbikeToDelete);
            return true;
        }).orElse(false);
        return result;
    }

}
