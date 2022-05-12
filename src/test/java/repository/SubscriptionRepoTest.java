package repository;

import com.example.bilabonnement_examproject.models.SubscriptionModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubscriptionRepoTest implements CRUDInterface <SubscriptionModel,Integer> {

    ArrayList<SubscriptionModel> allSubscription = new ArrayList<SubscriptionModel>(
            Arrays.asList(
                    new SubscriptionModel(1,true,true,100,3,"unlimited","11111111111111111",1,1,"2022-05-21"),
                    new SubscriptionModel(2,true,true,100,3,"unlimited","22222222222222222",1,2,"2022-05-21"),
                    new SubscriptionModel(2,true,true,100,3,"unlimited","33333333333333333",1,3,"2022-05-21"),
                    new SubscriptionModel(2,true,true,100,3,"unlimited","44444444444444444",1,4,"2022-05-21"),
                    new SubscriptionModel(2,true,true,100,3,"unlimited","55555555555555555",1,5,"2022-05-21")
            )
    );

    @Override
    public List<SubscriptionModel> getAllEntities() {
        return allSubscription;
    }

    @Override
    public SubscriptionModel getSingleEntity(Integer integer) {
        return null;
    }

    @Override
    public boolean createEntity(SubscriptionModel entity) {
        return false;
    }

    @Override
    public boolean updateEntity(Integer key) {
        return false;
    }
}
