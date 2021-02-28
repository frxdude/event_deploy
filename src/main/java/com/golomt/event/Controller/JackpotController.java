package com.golomt.event.Controller;

import com.golomt.event.DTO.*;
import com.golomt.event.Repository.EventRepository;
import com.golomt.event.Repository.UserRepository;
import com.golomt.event.Repository.WJackpotRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JackpotController {
    @Autowired
    WJackpotRepository jackRepo;
    @Autowired
    EventRepository eveRepo;
    @Autowired
    UserRepository userRepo;
    private String[] names;
    private int rand, count;
    private int[] counts = new int[8];

    // Jackpot
    @GetMapping("/JackpotNumber/accNum/{eventId}")
    public LottoDTO getJackpotNumberByAccNum(@PathVariable long eventId) {
        String lotNums = "", tempCont, winnerFirstName = "", winnerLastName = "";
        for (int i = 0; i < 6; i++) {
            do {
                tempCont = lotNums;
                if (tempCont.length() == 0) {
                    rand = (int) (Math.random() * 9) + 1;
                    tempCont += String.valueOf(rand);
                    System.out.println("rand is : " + rand);
                    System.out.println("tempCont is : " + tempCont);
                    names = userRepo.getFirstNamesByAccNum(tempCont, eventId);
                } else {
                    rand = (int) (Math.random() * 10);
                    tempCont += String.valueOf(rand);
                    names = userRepo.getFirstNamesByAccNum(tempCont, eventId);
                }
                count = names.length;
            } while (count == 0);
            counts[i] = count;
            System.out.println("COUNTS :  " + counts[i]);
            System.out.println("LOTTO NUM :  " + lotNums);
            lotNums += String.valueOf(rand);
        }
        List<AvailUsersDTO> availUsers = userRepo.getAvailFirstNamesByAccNum(lotNums, eventId);
        WinnerDTO winner = userRepo.getWinnerInfoByAccNum(lotNums, eventId);
        LottoDTO message = new LottoDTO(winner, counts, availUsers);
        return message;
    }

    @GetMapping("/JackpotNumber/telNum/{eventId}")
    public LottoDTO1 getJackpotNumberByTelNum(@PathVariable long eventId) {
        String lotNums = "", tempCont, winnerFirstName = "", winnerLastName = "";
        for (int i = 0; i < 8; i++) {
            do {
                tempCont = lotNums;
                if (tempCont.length() == 0) {
                    rand = (int) (Math.random() * 9) + 1;
                    tempCont += String.valueOf(rand);
                    names = userRepo.getFirstNamesByTelNum(tempCont, eventId);
                } else {
                    rand = (int) (Math.random() * 10);
                    tempCont += String.valueOf(rand);
                    names = userRepo.getFirstNamesByTelNum(tempCont, eventId);
                }
                count = names.length;
            } while (count == 0);
            counts[i] = count;
            System.out.println("COUNTS :  " + counts[i]);
            System.out.println("LOTTO NUM :  " + lotNums);
            lotNums += String.valueOf(rand);
        }
        List<AvailUsersDTO1> availUsers = userRepo.getAvailFirstNamesByTelNum(lotNums, eventId);
        WinnerDTO1 winner = userRepo.getWinnerInfoByTelNum(lotNums, eventId);
        LottoDTO1 message = new LottoDTO1(winner, counts, availUsers);
        return message;
    }
}