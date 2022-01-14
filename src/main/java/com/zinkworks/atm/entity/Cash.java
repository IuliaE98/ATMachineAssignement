package com.zinkworks.atm.entity;

import lombok.Data;

@Data
public class Cash {
        private int nrOfEqualNotes;
        private int banknotes;

    public Cash(int nrOfEqualNotes, int banknotes) {
        this.nrOfEqualNotes = nrOfEqualNotes;
        this.banknotes = banknotes;
    }

    public boolean CheckTakeNote(){
            if(this.banknotes > 0)
                return true;
            return false;

        }

        public boolean TakeNotes(int requestedNotes){
            if (this.banknotes >= requestedNotes)
            {
                this.banknotes = this.banknotes -  requestedNotes;
                return true;
            }
            return false;
        }

}

