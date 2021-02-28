package com.golomt.event.Controller;

import com.golomt.event.Analysis.ColorThief;
import com.golomt.event.DTO.LottoDTO;
import com.golomt.event.DTO.WheelPrizeDTO;
import com.golomt.event.Model.*;
import com.golomt.event.Repository.EventRepository;
import com.golomt.event.Repository.UserRepository;
import com.golomt.event.Repository.WLotteryRepository;
import com.golomt.event.Repository.WPrizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WheelController {
    @Autowired
    WLotteryRepository lotRepo;
    @Autowired
    WPrizeRepository prizeRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    EventRepository eveRepo;
    private String LotNums = "";
    private int loopCount = 0;
    private static int superPrize = 1;
    private static int bigPrize = 10;
    private static int smalPrize = 100;
    private static int index = 0;
    private static String[] prizeNames, prizeTotal, changedTotal;
    private ColorThief theif;
    // Custom
    @PostMapping("/spinTheWheel/{eventId}/{telNum}")
    public ResponseEntity<?> getRandomNum(@PathVariable String telNum, @PathVariable long eventId) throws IOException {
        WheelPrizeDTO prizeDTO = new WheelPrizeDTO();
        Event event = eveRepo.findById(eventId).get();
        System.out.println(event.getPrizeName());
        System.out.println(event.getPrizeTotal());
        prizeNames = event.getPrizeName().split("\\|");
        prizeTotal = event.getPrizeTotal().split("\\|");
        changedTotal = event.getChangedTotal().split("\\|");

        User user = userRepo.getInfoByTelnum(telNum, eventId);

        int spin = user.getSpin(), size = prizeTotal.length, theIndex = 0;


        if (spin == 0) {
            prizeDTO.setSpinTotal(0);
            prizeDTO.setSpin(false);
            prizeDTO.setPrize("");
            return new ResponseEntity<>(prizeDTO, HttpStatus.OK);
        }
        //init probability
        Double [] probability = new Double[size+1];
        probability[0] = 0.0;

        for(int i = 0; i<size ; i++)
        {
//            probability[i+1] = Double.valueOf(String.format("%.4f", (new Double(prizeTotal[i]) / size)));
            probability[i+1] = new Double(prizeTotal[i]) / new Double(prizeTotal[prizeTotal.length-1]);
            System.out.println("probability[" + (i+1) + "]" + probability[i]);
        }

        boolean check = false;

        while(!check)
        {
            Double rand = Math.random();
            String newTotal = "";
            for(int i = 1; i<=size ; i++)
                if(probability[i-1] < rand && rand < probability[i]
                        && Integer.valueOf(changedTotal[i-1]) != 0)
                {
                    theIndex = i-1;
                    prizeDTO.setPrize(prizeNames[i-1]);
                    int totalOfIndex = Integer.valueOf(changedTotal[i-1]);
                    for(int j = 0 ; j<size; j++)
                    {
                        if(j == i-1)
                            newTotal += --totalOfIndex + "|";
                        else if(j!=size-1)
                            newTotal += changedTotal[j] + "|";
                        else
                            newTotal += changedTotal[j];
                    }
                    System.out.println("OLD : " + event.getPrizeTotal());
                    System.out.println("NEW : " + newTotal);
                    event.setChangedTotal(newTotal);
                    eveRepo.save(event);
                    check = true;
                }

        }
//        for(int i = 0 ; i < size ; i++)
//        {
//            if((int)(Math.log10(Integer.valueOf(prizeTotal[i])) + 1) < 1)
//                probability[i] = ;
//        }

//        for (int i = 0; i < size; i++)
//        {
//            double randomFitness = Math.random() * Integer.valueOf(prizeTotal[prizeTotal.length - 1]);
//            int index = Arrays.binarySearch(prizeNames, randomFitness);
//            if (index < 0)
//                index = Math.abs(index + 1);
//            System.out.println("THIS IS THE INDEX : " + index);
//            System.out.println("THIS IS THE PRIZE : " + prizeNames[index]);
//        }
//        int one = 0;
//        int two = 0;
//        int three = 0;
//        int four = 0;
//        int five = 0;
//        int six = 0;
//
//        for(int i = 0 ; i<300 ; i++)
//        {
//            double rand = Math.random();
//            if(rand <= 0.0066)
//                one++;
//            else if(rand > 0.0066 && rand <=0.0166)
//                two++;
//            else if(rand > 0.0166 && rand <=0.0333)
//                three++;
//            else if(rand > 0.0333 && rand <=0.3333)
//                four++;
//            else if(rand > 0.3333 && rand <=0.5)
//                five++;
//            else if(rand > 0.5 && rand <=1)
//                six++;
//        }
//        System.out.println("1. " +one+ "\n2. " +two+ "\n3. " +three+ "\n4. " +four+ "\n5. " +five+ "\n6. " +six);
//        for(int i = 0 ; i < size ; i++)
//        {
//            while (check == 0) {
//                Double rand = Math.random() * size;
//                if (rand <= 0.02 && superPrize != 0) {
//                    prizeDTO.setPrize("SUPER Prize!");
//                    ++check;
//                } else if (rand > 0.02 && rand <= 0.2 && bigPrize != 0) {
//                    prizeDTO.setPrize("Big Prize!");
//                    ++check;
//                } else if (rand > 0.2 && rand <= 1 && smalPrize != 0) {
//                    prizeDTO.setPrize("Small Prize!");
//                    ++check;
//                }
//                if (superPrize == 0 && bigPrize == 0 && smalPrize == 0)
//                    return new ResponseEntity<>("No prize left!", HttpStatus.OK);
//            }
//        }
        user.setSpin(--spin);
        userRepo.save(user);
        prizeDTO.setPrizeIndex(theIndex);
        prizeDTO.setSpin(true);
        prizeDTO.setSpinTotal(user.getSpin());

        System.out.println("-----------------");
        System.out.println(prizeDTO.getPrize());
        System.out.println(prizeDTO.getSpinTotal());
        System.out.println(prizeDTO.isSpin());
        System.out.println(prizeDTO.getPrizeIndex());
        System.out.println("-----------------");

        return new ResponseEntity<>(prizeDTO, HttpStatus.OK);
    }

    // Lottery
    @GetMapping("/Lottery")
    public List<WJackpot> getLottery() {
        lotRepo.findAll();
        return null;
    }

    @PostMapping("/Lottery")
    public ResponseEntity<?> addLottery(@RequestBody WLottery lottery) {
        lotRepo.save(lottery);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/Lottery")
    public ResponseEntity<?> updateLottery(@RequestBody WLottery lottery) {
        WLottery oldLottery = lotRepo.findById(lottery.getId()).get();
        oldLottery.setAlive(lottery.isAlive());
        oldLottery.setDescription(lottery.getDescription());
        oldLottery.setName(lottery.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/Lottery")
    public ResponseEntity<?> deleteLottery(long id) {
        if (!lotRepo.findById(id).equals(null)) {
            lotRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Prize
    @GetMapping("/Prize")
    public List<WPrize> getPrize() {
        return prizeRepo.findAll();
    }

    @PostMapping("/Prize")
    public ResponseEntity<?> addPrize(@RequestBody WPrize prize) {
        prizeRepo.save(prize);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/Prize")
    public ResponseEntity<?> updatePrize(WPrize prize) {
        WPrize oldPrize = prizeRepo.findById(prize.getId()).get();
        oldPrize.setAlive(prize.isAlive());
        oldPrize.setDescription(prize.getDescription());
        oldPrize.setName(prize.getName());
        oldPrize.setType(prize.getType());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // Role
    // User
    // UserAuth
}
