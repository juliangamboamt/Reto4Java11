package Service;

import Model.Score;
import Repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll(){
        return (List<Score>) scoreRepository.getAll();
    }

    public Optional<Score> getScore(int id){

        return scoreRepository.getScore(id);
    }

    public Score save(Score score){
        if(score.getIdScore()== null){
            return scoreRepository.save(score);
        }else{
            Optional<Score> scoreFound = scoreRepository.getScore(score.getIdScore());
            if(scoreFound.isEmpty()){
                return scoreRepository.save(score);
            }else{
                return score;
            }
        }
    }

    public Score update(Score score){
        if(score.getIdScore()!= null){
            Optional<Score> scoreFound = scoreRepository.getScore(score.getIdScore());
            if(!scoreFound.isEmpty()){
                if(score.getMessageText()!= null){
                    scoreFound.get().setMessageText(score.getMessageText());
                }
                if(score.getStars()!= null){
                    scoreFound.get().setStars(score.getStars());
                }
                return scoreRepository.save(scoreFound.get());
            }
        }
        return score;
    }

    public boolean deleteScore(int scoreId){
        Boolean result = getScore(scoreId).map(scoreToDelete ->{
            scoreRepository.delete(scoreToDelete);
            return true;
        }).orElse(false);
        return result;
    }
}
