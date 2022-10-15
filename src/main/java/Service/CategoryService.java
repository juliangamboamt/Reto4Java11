package Service;

import Model.Category;
import Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return (List<Category>) categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id){

        return categoryRepository.getCategory(id);
    }

    public Category save(Category category){
        if(category.getId() == null){
            return categoryRepository.save(category);
        }else{
            Optional<Category> categoryFound = categoryRepository.getCategory(category.getId());
            if(categoryFound.isEmpty()){
                return categoryRepository.save(category);
            }else{
                return category;
            }
        }
    }

    public Category update(Category category){
        if(category.getId() != null){
            Optional<Category> categoryFound = categoryRepository.getCategory(category.getId());
            if(!categoryFound.isEmpty()){
                if(category.getName() != null){
                    categoryFound.get().setName(category.getName());
                }
                if(category.getDescription() != null){
                    categoryFound.get().setDescription(category.getDescription());
                }
                return categoryRepository.save(categoryFound.get());
            }
        }
        return category;
    }

    public boolean deleteCategory(int CategoryId){
        Boolean result = getCategory(CategoryId).map(categoryToDelete ->{
            categoryRepository.delete(categoryToDelete);
            return true;
        }).orElse(false);
        return result;
    }
}
