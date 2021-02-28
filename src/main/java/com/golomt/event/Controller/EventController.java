
package com.golomt.event.Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.golomt.event.Analysis.ColorThief;
import com.golomt.event.DTO.*;
import com.golomt.event.Helper.SMSHelper;
import com.golomt.event.Model.*;
import com.golomt.event.Repository.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.opendevl.JFlat;
import com.golomt.event.Helper.ImageResizer;
import com.golomt.event.Helper.QRCodeGenerator;
import com.golomt.event.LinkTable.EventUserRole;
import com.golomt.event.LinkTable.UserEvent;
import com.golomt.event.Service.UserService;
import com.google.zxing.WriterException;

import net.minidev.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventController {
    OkHttpClient client = new OkHttpClient();
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AccessRepository accRepo;
    @Autowired
    private EventRepository eveRepo;
    @Autowired
    private EventUserRepository eventUserRepo;
    @Autowired
    private EventUserRoleRepository eventUserRoleRepo;
    @Autowired
    private InvolvedEventRepository iEveRepo;
    @Autowired
    private InvitationRepository invRepo;
    @Autowired
    private UserAuthRepository userAuthRepo;
    @Autowired
    private UserEventRepository userEventRepo;
    @Autowired
    private EventRepository eventRepo;
    @Autowired
    private SmsRepository smsRepo;
    @Autowired
    private EventMsgRepository eventMsgRepo;
    @Autowired
    private UserService notificationService;
    @Autowired
    private EventFieldsRepository eveFieldsRepo;

    ColorThief theif = new ColorThief();
    ImageResizer resizer = new ImageResizer();
    QRCodeGenerator QRCG = new QRCodeGenerator();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    private Logger logger = LoggerFactory.getLogger(WheelController.class);

    /*
     * Web
     */

    @PostMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        try {
            Optional<User> user = userRepo.findById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/userExistsByEmail/{email}")
    public ResponseEntity<?> userExistsByEmail(@PathVariable String email) {
        try {
            boolean check = userRepo.existsByEmail(email);
            return new ResponseEntity<>(check, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/userExistsByAccessNumber/{accessNumber}")
    public ResponseEntity<?> userExistsByAccessNumber(@PathVariable String accessNumber) {
        try {
            boolean check = userRepo.existsByAccessNumber(accessNumber);
            return new ResponseEntity<>(check, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/getInfoByTelNum/{eventId}/{telNum}")
    public User getUserByTelNum(@PathVariable String telNum, @PathVariable long eventId) {
        return userRepo.getInfoByTelnum(telNum, eventId);
    }

    @PostMapping("/getUsers")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/getAccessNumbers")
    public List<AccessNumber> getAccessNumbers() {
        return accRepo.findAll();
    }

//    @PostMapping("/getEvents")
//    public List<Event> getEvents() {
//        int count = userRepo.getCountOfUsers();
//        List<Event> events = eveRepo.findAll();
//        events.forEach(e -> {
//            e.setEventAllUserNumber(count);
//            e.setEventInvolvedUserNumber(iEveRepo.countByEventId(e.getId()));
//        });
//        return events;
//    }

    @PostMapping("/getEvents")
    public List<EventDTO> getEvents() {
        int count = userRepo.getCountOfUsers();
        List<EventDTO> eventDTO = new ArrayList<>();
        List<EventFields> eventFields = eveFieldsRepo.findAll();
        List<Event> events = eveRepo.findAll();
        events.forEach(e -> {
            e.setEventAllUserNumber(count);
            e.setEventInvolvedUserNumber(iEveRepo.countByEventId(e.getId()));
            EventDTO temp = new EventDTO();
            temp.setId(e.getId());
            temp.setActive(e.isActive());
            temp.setChangedTotal(e.getChangedTotal());
            temp.setCoverPic(e.getCoverPic());
            temp.setDelFlg(e.isDelFlg());
            temp.setEventAllUserNumber(e.getEventAllUserNumber());
            temp.setEventDescription(e.getEventDescription());
            temp.setEventEndTime(e.getEventEndTime());
            temp.setEventInvolvedUserNumber(e.getEventInvolvedUserNumber());
            temp.setEventName(e.getEventName());
            temp.setEventStartTime(e.getEventStartTime());
            temp.setEventWhere(e.getEventWhere());
            temp.setLottoType(e.getLottoType());
            temp.setMainColor(e.getMainColor());
            temp.setPic1(e.getPic1());
            temp.setPic2(e.getPic2());
            temp.setPic3(e.getPic3());
            temp.setPrizeName(e.getPrizeName());
            temp.setPrizePic(e.getPrizePic());
            temp.setPrizeTotal(e.getPrizeTotal());
            temp.setVideoLink(e.getVideoLink());
            temp.setWebLink(e.getWebLink());
            temp.setEveFields(eveFieldsRepo.getFieldsByEventId(e.getId()));
            eventDTO.add(temp);
        });
        return eventDTO;
    }
    //	@RequestBody okhttp3.RequestBody formBody
    @PostMapping("/SocialInfo")
    public ResponseEntity<?> getSocialInfo(@RequestBody JSONObject formBody) throws IOException {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, formBody.toString());
        Request request = new Request.Builder().url("https://p2p.golomtbank.com/rpc/smartapptoken/")
                .post(okhttp3.RequestBody.create(formBody.toJSONString().getBytes()))
                .addHeader("Content-Type", "application/json; charset=UTF-8").build();
        try {
            Response response = client.newCall(request).execute();
            String realResponse = response.body().string();
            return new ResponseEntity<>(realResponse, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<>("Oldsongui", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@PathVariable long userId) {
        User oldUser = userRepo.findById(userId).get();
        oldUser.setDel_flg(!oldUser.isDel_flg());
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) throws WriterException, IOException {
        System.out.println(userDTO.toString());
        Event event = eveRepo.findById(userDTO.getEventId()).get();
//		if (userDTO.getSocialId() == null) {
//			long socialId = 0;
//			while (userRepo.existsBySocialId(String.valueOf(socialId))) {
//				socialId++;
//			}
//		}
        User oldUser;
        if (userDTO.getSocialId() == null)
            oldUser = userRepo.findBySocialId("0").orElse(null);
        else
            oldUser = userRepo.findBySocialId(userDTO.getSocialId()).orElse(null);
        boolean isDeleted = false, isUsedAccNum;
        String accNum;
        System.out.println("EVENT ::::::::::::::: " + event.toString());
//        if (event.getFieldName().contains("accessNumber") && accRepo.existsByAccessNumber(userDTO.getAccessNumber())) {
//            isUsedAccNum = accRepo.isUsedAccessNumber(userDTO.getAccessNumber());
//            accNum = userDTO.getAccessNumber();
//        } else {
            accNum = "0";
            isUsedAccNum = false;
//        }
        if (oldUser != null) {
            isDeleted = oldUser.isDel_flg();
            if (!oldUser.isDel_flg())
                return new ResponseEntity<>("Already registered", HttpStatus.BAD_REQUEST);
        }
        if (accRepo.existsByAccessNumber(accNum)) {
            if (!isUsedAccNum || isDeleted) {
//            if (isDeleted) {
                if (userDTO.getEventId() == 288 || !userRepo.existsByEmail(userDTO.getEmail()) || isDeleted) {
                    User user = new User();
                    user.setFirstName(userDTO.getFirstName());
                    user.setAccessNumber(accNum);
                    user.setCompanyName(userDTO.getCompanyName());
                    user.setEmail(userDTO.getEmail());
                    user.setLastName(userDTO.getLastName());
                    user.setPosition(userDTO.getPosition());
                    user.setTelNumber(userDTO.getTelNumber());
                    user.setSocialId(userDTO.getSocialId());
                    user.setInvolvedTime(now.toString());
                    user.setDel_flg(false);
                    user.setStatus("Pending");
                    user.setPaid(0);
                    user.setRegNum(userDTO.getRegNum());
                    user.setEventId(userDTO.getEventId());
                    UserEvent userEvent = new UserEvent();
                    userEvent.setEventId(userDTO.getEventId());
                    userEvent.setSocialId(userDTO.getSocialId());

                    String temp = "";
                    String accNUM = user.getAccessNumber();
                    String[] parts = {accNUM.substring(0, accNUM.length() / 2),
                            accNUM.substring(accNUM.length() / 2)};
                    temp = parts[0] + user.getId() + parts[1];
                    user.setEncodedData(Base64.getEncoder().encodeToString(temp.getBytes()));
                    user.setQrBase64(QRCG.generateQRCode64(userDTO.getEventId(), user.getEncodedData()));
                    if (!user.getAccessNumber().equals("0"))
                        accRepo.changeToUsed(accRepo.getAccessNumberIdByAccessNumber(user.getAccessNumber()));
                    else
                        user.setAccessNumber(null);
                    userRepo.save(user);
                    userEventRepo.save(userEvent);
                    try {
                        notificationService.sendMail(userDTO, user.getQrBase64(),
                                eventRepo.findById(userDTO.getEventId()).get());

                    } catch (Exception e) {
                        logger.info("Error:" + e.getMessage());
                    }

                    try {
                        SMSHelper smsHelper = new SMSHelper();
                        EventMsg evMsg = eventMsgRepo.findByEventId(userDTO.getEventId()).orElse(null);
                        if (evMsg == null) {
                            evMsg = new EventMsg();
                            evMsg.setEventId(userDTO.getEventId());
                            evMsg.setText("");
                        }
                        smsHelper.sendSMS(new SMSDTO(evMsg.getText() + userDTO.getAccessNumber() + "\n" + user.getQrBase64(), userDTO.getTelNumber()));
                        Sms sms = new Sms();
                        sms.setDateTime(new Date());
                        sms.setEventId(userDTO.getEventId());
                        sms.setTelNo(userDTO.getTelNumber());
                        sms.setText(evMsg.getText() + userDTO.getAccessNumber() + "\n\n" + user.getQrBase64());
                        smsRepo.save(sms);

                    } catch (Exception e) {
                        logger.info("Error:" + e.getMessage());
                        System.out.println("ERROR::::::: " + e.getMessage());
                    }

                    return new ResponseEntity<>(user.getQrBase64(), HttpStatus.OK);

                } else {
                    return new ResponseEntity<>("Used Email", HttpStatus.BAD_REQUEST);

                }
            } else
                return new ResponseEntity<>("Used Access Number", HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>("Access Number Not Found", HttpStatus.NOT_FOUND);

//		if (accRepo.existsByAccessNumber(userDTO.getAccessNumber())) {
//
//			if (!check) {
//				if (userRepo.existsByEmail(userDTO.getEmail()))
//					return new ResponseEntity<>("Used Email", HttpStatus.OK);
//				else {
//					User user = new User();
//					user.setFirstName(userDTO.getFirstName());
//					user.setAccessNumber(accNum);
//					user.setCompanyName(userDTO.getCompanyName());
//					user.setEmail(userDTO.getEmail());
//					user.setLastName(userDTO.getLastName());
//					user.setPosition(userDTO.getPosition());
//					user.setTelNumber(userDTO.getTelNumber());
//					user.setSocialId(userDTO.getSocialId());
//					user.setInvolvedTime(now.toString());
//					user.setDel_flg(false);
//					user.setStatus("Pending");
//					user.setPaid(0);
//					userRepo.save(user);
//					UserEvent userEvent = new UserEvent();
//					userEvent.setEventId(userDTO.getEventId());
//					userEvent.setSocialId(userDTO.getSocialId());
//					userEventRepo.save(userEvent);
//					user.setQrBase64(QRCG.generateQRCode64(user.getEncodedData()));
//					accRepo.changeToUsed(accRepo.getAccessNumberIdByAccessNumber(user.getAccessNumber()));
//					try {
//						notificationService.sendMail(userDTO, user.getQrBase64(),
//								eventRepo.findById(userDTO.getEventId()).get());
//
//					} catch (Exception e) {
//						logger.info("Error:" + e.getMessage());
//					}
//					return new ResponseEntity<>(user.getQrBase64(), HttpStatus.OK);
//				}
//			} else
//				return new ResponseEntity<>("Used Access Number", HttpStatus.OK);
//
//		} else
//			return new ResponseEntity<>("Access Number Not Found", HttpStatus.OK);
    }

//	@GetMapping("/getInfoById")
//	public List<User> getInfoById(@RequestBody )

    @PostMapping("/getInfo/{eventId}")
    public List<User> getInfo(@PathVariable long eventId) {
        return userRepo.getInfo(eventId);
    }

    @PostMapping("/getCSV/{eventId}")
    public ResponseEntity<?> getCSV(@RequestBody CSVDTO csvDTO)
            throws FileNotFoundException, UnsupportedEncodingException, Exception {
        File csvFile = new File("src/main/resources/csv/" + csvDTO.getEventId() + ".csv");
        JFlat flatMe = new JFlat(userRepo.getInfo(csvDTO.getEventId()).toString());
        flatMe.json2Sheet().headerSeparator("/").write2csv("src/main/resources/csv/" + csvDTO.getEventId() + ".csv");
        notificationService.sendInfo(csvDTO.getEmail(), csvFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addAccessNumber")
    public AccessNumber addAccessNumber(@RequestBody AccessNumber accNum) {
        return accRepo.save(accNum);
    }

    @PostMapping("/isExists/{socialId}/{eventId}")
    public ResponseEntity<?> isExists(@PathVariable String socialId, @PathVariable long eventId) {
        if (userRepo.existsBySocialId(socialId)) {
            if (userEventRepo.checkUserEvent(socialId, eventId).equals(BigInteger.ONE))
                return new ResponseEntity<>(userRepo.getQrBase64BySid(socialId), HttpStatus.OK);
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    @PostMapping("/addUserToEvent")
    public ResponseEntity<?> addUserToEvent(@RequestBody InvolvedEvent involvedEvent) {
        try {
            if (!userRepo.existsByEncodedData(involvedEvent.getUserEncodedData())) {
                if (!invRepo.existsByBranch(involvedEvent.getUserEncodedData())) {
                    return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
                } else
                    invRepo.incrementUsedCount(invRepo.getInvitationIdByBranch(involvedEvent.getUserEncodedData(), involvedEvent.getEventId()));
            }
            return new ResponseEntity<>(iEveRepo.save(involvedEvent), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addEvent")
    public Event addEvent(@RequestBody EventDTO event) throws IOException {
        Event newEvent = new Event();
        newEvent.setActive(event.isActive());
        newEvent.setChangedTotal(event.getChangedTotal());
        newEvent.setActive(event.isActive());
        newEvent.setCoverPic(event.getCoverPic());
        newEvent.setDelFlg(event.isDelFlg());
        newEvent.setEventDescription(event.getEventDescription());
        newEvent.setEventEndTime(event.getEventEndTime());
        newEvent.setEventName(event.getEventName());
        newEvent.setEventStartTime(event.getEventStartTime());
        newEvent.setEventWhere(event.getEventWhere());
        newEvent.setVideoLink(event.getVideoLink());
        newEvent.setWebLink(event.getWebLink());
        newEvent.setLottoType(event.getLottoType());
        newEvent.setPrizeName(event.getPrizeName());
        newEvent.setPrizePic(event.getPrizePic());
        newEvent.setPrizeTotal(event.getPrizeTotal());
        newEvent.setSelectedLottery(event.getSelectedLottery());
        Event returnEvent = eveRepo.save(newEvent);
        List<EventFields> eventFields = event.getEveFields();
        if(eventFields != null)
        for(int i=0 ;i<eventFields.size() ; i++)
        {
            EventFields tempEve = new EventFields();
            tempEve.setFieldName(eventFields.get(i).getFieldName());
            tempEve.setEventId(returnEvent.getId());
            tempEve.setFieldTitle(eventFields.get(i).getFieldTitle());
            tempEve.setFieldType(eventFields.get(i).getFieldType());
            tempEve.setMaxValue(eventFields.get(i).getMaxValue());
            tempEve.setMinValue(eventFields.get(i).getMinValue());
            tempEve.setOptions(eventFields.get(i).getOptions());
            tempEve.setRequired(eventFields.get(i).isRequired());
            tempEve.setValueType(eventFields.get(i).getValueType());
            eveFieldsRepo.save(tempEve);
        }
//        URL url = new URL(event.getCoverPic());
        if(event.getCoverPic() != "")
        {
            theif = new ColorThief();
            int[] tempColorList = theif.getColor(new URL(event.getCoverPic()));
            String RGBString = tempColorList[0]+"|"+tempColorList[1]+"|"+tempColorList[2];
            newEvent.setMainColor(RGBString);
        }
        newEvent.setActive(true);
        eventRepo.save(newEvent);
        System.out.println(returnEvent.toString());
        return returnEvent;
    }

    // upload image
    @PostMapping("/uploadPic")
    public ResponseEntity<?> uploadFile(@RequestBody File file) {
        String message = "";
        try {
            message = "Uploaded the file successfully: ";
            BufferedImage temp = resizer.resizer(file);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message = "Could not upload the file: ";
            return new ResponseEntity<>(message, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/getAccessNumberInfoById/{accessNumber}")
    public ResponseEntity<?> getAccessNumberInfoById(@PathVariable String accessNumber) {
        try {
            AccessNumber accInfo = accRepo.findByAccessNumber(accessNumber);
            return new ResponseEntity<>(accInfo, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/getUsersByEventId/{eventId}")
    public ResponseEntity<?> getUsersByEventId(@PathVariable int eventId) {
        try {
            return new ResponseEntity<>(userRepo.getUsersByEventId(eventId), HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable String id) {
        userRepo.deleteById(id);
    }

    @PutMapping("/deleteUserFromEvent/{eventId}/{userEncodedData}")
    public ResponseEntity<?> deleteUserById(@PathVariable long eventId, @PathVariable String userEncodedData) {
        try {
            User oldUser = userRepo.getUserByEIDnEData(eventId, userEncodedData);
            oldUser.setDel_flg(true);
            userRepo.save(oldUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // return roll names

    @PostMapping("/getRoleNames")
    public ResponseEntity<?> getRoleNames() {
        return new ResponseEntity<>(roleRepo.findAll(), HttpStatus.OK);
    }

    /*
     *
     * Event User Handle
     *
     */

    @PostMapping("/eventUserInfo")
    public ResponseEntity<?> addInfo(@RequestBody EventUserDTO eventUser) {

        EventUser eu = new EventUser();
        eu.setActive(eventUser.isActive());
        eu.setCompanyName(eventUser.getCompanyName());
        eu.setDelFlg(eventUser.isDelFlg());
        eu.setFirstName(eventUser.getFirstName());
        eu.setLastName(eventUser.getLastName());
        eu.setTelNumber(eventUser.getTelNumber());
        eu.setUserName(eventUser.getUserName());
        eu.setUploadAccess(eventUser.isUploadAccess());
        eu.setJackAccess(eventUser.isJackAccess());
        eu.setQrAccess(eventUser.isQrAccess());
        eventUserRepo.save(eu);
        System.out.println("THIS IS ID  ::::::::: " + eu.getId());

        EventUserRole eur = new EventUserRole();
        eur.setEventUserId(eu.getId());
        eur.setRoleId(eventUser.getRoleId());
        System.out.println("THIS IS ID  ::::::::: " + eur.getEventUserId());
        eventUserRoleRepo.save(eur);

        UserAuth ua = new UserAuth();
        ua.setPassword(Base64.getEncoder().encodeToString(eventUser.getPassword().getBytes()));
        ua.setUserId(eu.getId());
        userAuthRepo.save(ua);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/eventUserInfo")
    public ResponseEntity<?> updateInfo(@RequestBody EventUserDTO eventUserDTO) {
        long user_id = eventUserDTO.getId();
        EventUser oldUser = eventUserRepo.findById(user_id).get();
        UserAuth oldAuth = userAuthRepo.findByUserId(user_id);
        oldAuth.setPassword(eventUserDTO.getPassword());
        oldUser.setCompanyName(eventUserDTO.getCompanyName());
        oldUser.setUserName(eventUserDTO.getUserName());
        oldUser.setFirstName(eventUserDTO.getFirstName());
        oldUser.setLastName(eventUserDTO.getLastName());
        oldUser.setTelNumber(eventUserDTO.getTelNumber());
        oldUser.setActive(eventUserDTO.isActive());
        return new ResponseEntity<>(eventUserRepo.save(oldUser), HttpStatus.OK);
    }

    @PutMapping("/deleteEventUser/{userId}")
    public ResponseEntity<?> deleteEventUser(@PathVariable long userId) {
        EventUser oldEventUser = eventUserRepo.findById(userId).get();
        oldEventUser.setDelFlg(true);
        oldEventUser.setActive(false);
        return new ResponseEntity<>(eventUserRepo.save(oldEventUser), HttpStatus.OK);
    }

    @PostMapping("/getEventUserInfo")
    public ResponseEntity<?> getEventUserInfo() {
        return new ResponseEntity<>(eventUserRepo.getEventUsers(), HttpStatus.OK);
    }

    @GetMapping("/getQrBase64ByTelNo/{eventId}/{telNo}")
    public ResponseEntity<?> getQrBase64ByTelNo(@PathVariable long eventId, @PathVariable String telNo) {
        if (userRepo.getCountByTelNumberNEID(telNo, eventId) != 0)
            return new ResponseEntity<>(userRepo.getQrBase64ByTelNo(telNo, eventId), HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/deleteEvent/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable long eventId) {
        Event oldEvent = eventRepo.findById(eventId).get();
        oldEvent.setDelFlg(true);
        oldEvent.setActive(false);
        return new ResponseEntity<>(eventRepo.save(oldEvent), HttpStatus.OK);
    }

    @PutMapping("/updateEvent")
    public ResponseEntity<?> updateEvent(@RequestBody Event event) {

        Event oldEvent = eventRepo.findById(event.getId()).get();
        oldEvent.setActive(event.isActive());
        oldEvent.setCoverPic(event.getCoverPic());
        oldEvent.setEventDescription(event.getEventDescription());
        oldEvent.setEventEndTime(event.getEventEndTime());
        oldEvent.setEventName(event.getEventName());
        oldEvent.setEventStartTime(event.getEventStartTime());
        oldEvent.setEventWhere(event.getEventWhere());
//        oldEvent.setFieldName(event.getFieldName());
//        oldEvent.setFieldType(event.getFieldType());
//        oldEvent.setMinValue(event.getMinValue());
//        oldEvent.setMaxValue(event.getMaxValue());
//        oldEvent.setRequired(event.isRequired());
//        oldEvent.setOptions(event.getOptions());
        oldEvent.setVideoLink(event.getVideoLink());
        oldEvent.setWebLink(event.getWebLink());
        return new ResponseEntity<>(eventRepo.save(oldEvent), HttpStatus.OK);
    }

    @DeleteMapping("/eventUserInfo")
    public ResponseEntity<?> deleteInfo(@RequestBody EventUser eventUser) {
        Optional<EventUser> euForDel = eventUserRepo.findById(eventUser.getId());
        eventUserRepo.delete(euForDel.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
     *
     * Event User Event Handle
     *
     */

    // eventId eventUserId garna
//	@GetMapping("/eventUserEvent")
//	public ResponseEntity<?> getEventUserEvent() {
//		return new ResponseEntity<>(eventUserEventRepo.findAll(), HttpStatus.OK);
//	}
//
//	@PostMapping("/eventUserEvent")
//	public ResponseEntity<?> getEventUserEvent(@RequestBody EventUserEvent eventUserEvent) {
//		return new ResponseEntity<>(eventUserEventRepo.save(eventUserEvent), HttpStatus.OK);
//	}

    @PostMapping("/getEventUsersByEventId/{eventId}")
    public ResponseEntity<?> getEventUsersByEventId(@PathVariable long eventId) {
        return new ResponseEntity<>(eventUserRepo.getEventUsersByEventId(eventId), HttpStatus.OK);
    }
//
//	@PostMapping("/eventUserEvent")
//	public EventUserEvent addEventUserEvent(@RequestBody EventUserEvent eue) {
//		return eventUserEventRepo.save(eue);
//	}

//	@DeleteMapping("/eventUserEvent")
//	public ResponseEntity<?> deleteEventUserEvent(@RequestBody EventUserEvent eue) {
//		Optional<EventUserEvent> eueForDel = eventUserEventRepo.findById(eue.getId());
//		eventUserEventRepo.delete(eueForDel.get());
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
    /*
     *
     *
     * LOGIN AUTH
     *
     *
     *
     */

    @PostMapping("/login")
    public ResponseEntity<?> toLogin(@RequestBody AuthDTO authDTO) {
        String userName = authDTO.getUserName();
        if (eventUserRepo.existsByUserName(userName)) {
            String encodedPass = Base64.getEncoder().encodeToString(authDTO.getPassword().getBytes());
            long id = eventUserRepo.getUserIdByUserName(userName);
            EventUser eu = eventUserRepo.findById(id).get();
            if (eventUserRepo.existsByUserName(userName)) {
                System.out.println(userAuthRepo.toLogin(id, encodedPass));
                try {
                    if (userAuthRepo.toLogin(id, encodedPass).equals(BigInteger.ONE))
                        if (eu.isActive()) {
                            LoginDTO temp = eventUserRoleRepo.getRoleIdByEventUserId(id);
                            System.out.println("Its temp : " + temp.getRoleId());
                            return new ResponseEntity<>(temp, HttpStatus.OK);
                        } else {
                            System.out.println("WTF IS THAT  ::::");
                            return new ResponseEntity<>("inActive", HttpStatus.FORBIDDEN);
                        }
                    else
                        return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
                } catch (Exception e) {
                    System.out.println("error: " + e);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }

    /*
     *
     * User Event check with social id and event id
     *
     */
    @PostMapping("/addToUserEvent")
    public UserEvent addToUserEvent(@RequestBody UserEvent userEvent) {
        return userEventRepo.save(userEvent);
    }

}
