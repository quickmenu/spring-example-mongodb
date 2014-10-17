package net.sjune.mongodb.persistence.dao;

import net.sjune.mongodb.persistence.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.ReadPreference;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	MongoTemplate mongoTemplate;
	
	private static String COLLECTION_NAME = "testcollect";

	@Override
	public User insert(User user) {
		mongoTemplate.insert(user, COLLECTION_NAME);
		return user;
	}

	@Override
	public User getUser(User user) {
		return mongoTemplate.findById(user.getUserName(), User.class,
				COLLECTION_NAME);
	}

	@Override
	public List<User> getUsers() {
		// 데이터 베이스 상태체크(변경서버 확인)
		System.out.println(mongoTemplate.getDb().getStats().getServerUsed());

		// mongoDB SECONDARY sever 읽기 우선순위 지정(테스트 완료)
		mongoTemplate.getDb().setReadPreference(ReadPreference.SECONDARY);
		System.out.println(mongoTemplate.getDb().getReadPreference());
		
		return (List<User>) mongoTemplate.findAll(User.class, COLLECTION_NAME);
	}

	@Override
	public void deleteUser(User user) {
		Query query = new Query(new Criteria("id").is(user.getUserName()));
		mongoTemplate.remove(query, COLLECTION_NAME);
	}

	@Override
	public User updateUser(User user) {
		Query query = new Query(new Criteria("id").is(user.getUserName()));

		Update update = new Update();
		update.set("userName", user.getUserName());
		update.set("password", user.getPassword());

		mongoTemplate.updateFirst(query, update, COLLECTION_NAME);

		return user;
	}

}
