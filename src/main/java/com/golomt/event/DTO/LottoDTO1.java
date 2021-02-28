package com.golomt.event.DTO;

import java.util.List;

public class LottoDTO1 {
    private WinnerDTO1 winnerDTO;
    private int[] counts;
    private List<AvailUsersDTO1> availUsers;

    public LottoDTO1(WinnerDTO1 winnerDTO, int[] counts, List<AvailUsersDTO1> availUsers) {
        this.winnerDTO = winnerDTO;
        this.counts = counts;
        this.availUsers = availUsers;
    }

    public LottoDTO1() {
    }

    public WinnerDTO1 getWinnerDTO() {
        return winnerDTO;
    }

    public void setWinnerDTO(WinnerDTO1 winnerDTO) {
        this.winnerDTO = winnerDTO;
    }

    public int[] getCounts() {
        return counts;
    }

    public void setCounts(int[] counts) {
        this.counts = counts;
    }

    public List<AvailUsersDTO1> getAvailUsers() {
        return availUsers;
    }

    public void setAvailUsers(List<AvailUsersDTO1> availUsers) {
        this.availUsers = availUsers;
    }
}
