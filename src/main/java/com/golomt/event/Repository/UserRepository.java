package com.golomt.event.Repository;

import java.util.List;
import java.util.Optional;

import com.golomt.event.DTO.AvailUsersDTO;
import com.golomt.event.DTO.AvailUsersDTO1;
import com.golomt.event.DTO.WinnerDTO;
import com.golomt.event.DTO.WinnerDTO1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.golomt.event.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	public static final String GET_UsersByEventId = "SELECT first_name, last_name, company_name, encoded_data, involved_time FROM users WHERE event_id = ?";
	public static final String GET_CountOfInvEveUsers = "SELECT COUNT(*) FROM involved_events WHERE involved_events.event_id = :eventId";
	public static final String GET_countOfUsers = "SELECT COUNT(*) FROM users";
	public static final String GET_IdByUserName = "SELECT eu.id from event_users eu WHERE eu.user_name = :userName";
	public static final String GET_qrBase64BySid = "SELECT u.qr_base64 from users u WHERE u.social_id = :socialId";
	public static final String GET_qrBase64ByTelNo = "SELECT u.qr_base64 from users u WHERE u.tel_number = :telNo AND u.event_id = :eventId AND NOT u.del_flg = 1";
	public static final String GET_InfoById = "SELECT u.access_number, u.last_name, u.first_name, u.company_name, u.position, u.tel_number, u.email, u.involved_time FROM users u WHERE u.id = :userId";
	public static final String GET_InfoByTelnum = "SELECT * FROM users WHERE tel_number = :telNum AND event_id = :eventId";
	public static final String GET_Info = "SELECT u.access_number, u.last_name, u.first_name, u.company_name, u.position, u.tel_number, u.email, u.involved_time FROM users u WHERE u.social_id in (SELECT uel.social_id FROM user_event_link uel WHERE uel.event_id = :eventId)";
	public static final String GET_countOfUsersByDigit = "SELECT COUNT(*) FROM access_numbers WHERE access_number LIKE :num%";
	public static final String GET_firstNamesByAccNum = "SELECT u.first_name FROM users u WHERE access_number LIKE :accNum% AND u.event_id = :eventId AND NOT u.del_flg = 1";
	public static final String GET_firstNamesByTelNum = "SELECT u.first_name FROM users u WHERE tel_number LIKE :telNum% AND u.event_id = :eventId AND NOT u.del_flg = 1";
	public static final String GET_availFirstNamesByAccNum = "SELECT u.first_name, u.access_number FROM users u WHERE LEFT(u.access_number,5) = LEFT(:lotNum,5) AND u.event_id = :eventId AND NOT u.del_flg = 1";
	public static final String GET_availFirstNamesByTelNum = "SELECT u.first_name, u.tel_number FROM users u WHERE LEFT(u.tel_number,7) = LEFT(:telNum,7) AND u.event_id = :eventId AND NOT u.del_flg = 1";
	public static final String GET_winnerInfoByAccNum = "SELECT u.first_name, u.last_name, u.access_number FROM users u WHERE u.access_number = :accNum AND u.event_id = :eventId AND u.del_flg = 0";
	public static final String GET_winnerInfoByTelNum = "SELECT u.first_name, u.last_name, u.tel_number FROM users u WHERE u.tel_number = :telNum AND u.event_id = :eventId AND u.del_flg = 0";
	public static final String GET_userByEventIdAndEnc = "SELECT * FROM users WHERE event_id = :eventId AND encoded_data = :encodedData";
	public static final String GET_countByTelNumberNEID = "SELECT COUNT(*) FROM users WHERE tel_number = :telNum AND event_id = :eventId";
	public Optional<User> findById(long id);
	public Optional<User> findByAccessNumber(String accNum);
	public Optional<User> findBySocialId(String socialId);

	public boolean existsByEmail(String email);

	public boolean existsBySocialId(String socialId);

	public boolean existsByAccessNumber(String accessNumber);

	public boolean existsByEncodedData(String encodedData);

	@Query(value = GET_countByTelNumberNEID, nativeQuery = true)
	public int getCountByTelNumberNEID(@Param("telNum") String telNum,@Param("eventId") long eventId);

	@Query(value = GET_firstNamesByAccNum, nativeQuery = true)
	public String[] getFirstNamesByAccNum(@Param("accNum") String accNum, @Param("eventId") long eventId);

	@Query(value = GET_firstNamesByTelNum, nativeQuery = true)
	public String[] getFirstNamesByTelNum(@Param("telNum") String telNum, @Param("eventId") long eventId);

	@Query(value= GET_Info, nativeQuery = true)
	public List<User> getInfo(@Param("eventId") long eventId);

	@Query(value= GET_InfoById, nativeQuery = true)
	public User getInfoById(@Param("userId") long userId);

	@Query(value= GET_InfoByTelnum, nativeQuery = true)
	public User getInfoByTelnum(@Param("telNum") String telNum, @Param("eventId") long eventId);

	@Query(value = GET_CountOfInvEveUsers, nativeQuery = true)
	public int getCountOfInvEveUsers(@Param("eventId") long eventId);

	@Query(value = GET_UsersByEventId, nativeQuery = true)
	public List<Object> getUsersByEventId(@Param("eventId") long eventId);

	@Query(value = GET_countOfUsers, nativeQuery = true)
	public int getCountOfUsers();

	@Query(value = GET_IdByUserName, nativeQuery = true)
	public long getIdByUsername(@Param("userName") String userName);

	@Query(value = GET_qrBase64BySid,nativeQuery = true)
	public String getQrBase64BySid(@Param("socialId") String socialId);

	@Query(value = GET_qrBase64ByTelNo,nativeQuery = true)
	public String getQrBase64ByTelNo(@Param("telNo") String telNo, @Param("eventId") long eventId);

	@Query(value = GET_winnerInfoByAccNum, nativeQuery = true)
	public WinnerDTO getWinnerInfoByAccNum(@Param("accNum") String accNum, @Param("eventId") long eventId);

	@Query(value = GET_winnerInfoByTelNum, nativeQuery = true)
	public WinnerDTO1 getWinnerInfoByTelNum(@Param("telNum") String telNum, @Param("eventId") long eventId);

	@Query(value = GET_availFirstNamesByAccNum, nativeQuery = true)
	public List<AvailUsersDTO> getAvailFirstNamesByAccNum(@Param("lotNum") String lotNum, @Param("eventId") long eventId);

	@Query(value = GET_availFirstNamesByTelNum, nativeQuery = true)
	public List<AvailUsersDTO1> getAvailFirstNamesByTelNum(@Param("telNum") String telNum, @Param("eventId") long eventId);

	@Query(value = GET_userByEventIdAndEnc, nativeQuery = true)
	public User getUserByEIDnEData(@Param("eventId") long eventId, @Param("encodedData") String encodedData);
}
