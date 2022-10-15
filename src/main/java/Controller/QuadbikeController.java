package Controller;

import Model.Quadbike;
import Service.QuadbikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Quadbike")
public class QuadbikeController {
    @Autowired
    private QuadbikeService quadbikeService;

    @GetMapping("/all")
    public List<Quadbike> getAll(){
        return quadbikeService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Quadbike> getQuadbike(@PathVariable("id")int id){
        return quadbikeService.getQuadbike(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Quadbike save(@RequestBody Quadbike quadbike){
        return quadbikeService.save(quadbike);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Quadbike update(@RequestBody Quadbike quadbike) {
        return quadbikeService.update(quadbike);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        quadbikeService.deleteQuadbike(id);
    }
}
