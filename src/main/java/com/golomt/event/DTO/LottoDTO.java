package com.golomt.event.DTO;

import java.util.List;

public class LottoDTO {
    private WinnerDTO winnerDTO;
    private int[] counts;
    private List<AvailUsersDTO> availUsers;

    public LottoDTO(WinnerDTO winnerDTO, int[] counts, List<AvailUsersDTO> availUsers) {
        this.winnerDTO = winnerDTO;
        this.counts = counts;
        this.availUsers = availUsers;
    }

    public LottoDTO() {
    }

    public WinnerDTO getWinnerDTO() {
        return winnerDTO;
    }

    public void setWinnerDTO(WinnerDTO winnerDTO) {
        this.winnerDTO = winnerDTO;
    }

    public int[] getCounts() {
        return counts;
    }

    public void setCounts(int[] counts) {
        this.counts = counts;
    }

    public List<AvailUsersDTO> getAvailUsers() {
        return availUsers;
    }

    public void setAvailUsers(List<AvailUsersDTO> availUsers) {
        this.availUsers = availUsers;
    }
}
