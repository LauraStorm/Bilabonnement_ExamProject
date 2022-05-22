package repository;

import com.example.bilabonnement_examproject.models.SubscriptionModel;
import com.example.bilabonnement_examproject.repositories.CRUDInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubscriptionRepoTest implements CRUDInterface <SubscriptionModel,Integer> {

    ArrayList<SubscriptionModel> allSubscription = new ArrayList<SubscriptionModel>(
            Arrays.asList(
                    new SubscriptionModel(1,true,true,100,3,"unlimited","11111111111111111",1,1,"2022-05-10", "2022-08-10"),
                    new SubscriptionModel(2,true,true,100,3,"unlimited","22222222222222222",1,2,"2022-06-10", "2022-09-10"),
                    new SubscriptionModel(3,true,true,100,3,"unlimited","33333333333333333",1,3,"2022-07-10", "2022-10-10"),
                    new SubscriptionModel(4,true,true,100,3,"unlimited","44444444444444444",1,4,"2022-08-10","2022-11-10"),
                    new SubscriptionModel(5,true,true,100,3,"unlimited","55555555555555555",1,5,"2022-09-10","2022-12-10")
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
    public boolean updateEntity(SubscriptionModel entity) {
        return false;
    }
}

