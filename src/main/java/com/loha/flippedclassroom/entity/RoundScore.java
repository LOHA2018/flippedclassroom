package com.loha.flippedclassroom.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * POJO
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Getter
@Setter
public class RoundScore {
    private Integer roundId;
    private Integer teamId;
    private BigDecimal totalScore;
    private BigDecimal presentationScore;
    private BigDecimal questionScore;
    private BigDecimal reportScore;

    private Round round;
    private Team team;

//    @Override
//    public int hashCode(){
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((roundId == null) ? 0 : roundId.hashCode());
//        result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
//        result = prime * result + ((totalScore == null) ? 0 : totalScore.hashCode());
//        result = prime * result + ((presentationScore == null) ? 0 : presentationScore.hashCode());
//        result = prime * result + ((questionScore == null) ? 0 : questionScore.hashCode());
//        result = prime * result + ((reportScore == null) ? 0 : reportScore.hashCode());
//        return result;
//    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//        {
//            return true;
//        }
//        if (obj == null)
//        {
//            return false;
//        }
//        if (getClass() != obj.getClass())
//        {
//            return false;
//        }
//        RoundScore other = (RoundScore) obj;
//        if (roundId == null) {
//            if (other.roundId != null)
//            {
//                return false;
//            }
//        } else if (!roundId.equals(other.roundId))
//        {
//            return false;
//        }
//
//        if (teamId == null) {
//            if (other.teamId != null)
//            {
//                return false;
//            }
//        } else if (!teamId.equals(other.teamId))
//        {
//            return false;
//        }
//
//        if (totalScore == null) {
//            if (other.totalScore != null)
//            {
//                return false;
//            }
//        } else if (!totalScore.equals(other.totalScore))
//        {
//            return false;
//        }
//
//        if (presentationScore == null) {
//            if (other.presentationScore != null)
//            {
//                return false;
//            }
//        } else if (!presentationScore.equals(other.presentationScore))
//        {
//            return false;
//        }
//
//
//        if (questionScore == null) {
//            if (other.questionScore != null)
//            {
//                return false;
//            }
//        } else if (!questionScore.equals(other.questionScore))
//        {
//            return false;
//        }
//
//        if (reportScore == null) {
//            if (other.reportScore != null)
//            {
//                return false;
//            }
//        } else if (!reportScore.equals(other.reportScore))
//        {
//            return false;
//        }
//
//        return true;
//    }
}
