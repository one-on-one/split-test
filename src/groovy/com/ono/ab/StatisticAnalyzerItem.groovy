package com.ono.ab

class StatisticAnalyzerItem {

    int id
    String name
    int hitCount
    int convertCount
    int actionOneCount
    int actionTwoCount
    int actionThreeCount

    def Float convertPercentage() {
        if (hitCount > 0) {
            return (convertCount.toFloat() / hitCount.toFloat())
        } else {
            return 0.0
        }
    }

    def Float marginOfError(StatisticAnalyzerItem champion) {
        if (champion.hitCount > hitCount) {
            return 1 - (hitCount.toFloat() / champion.hitCount.toFloat())
        } else {
            return 1 - (champion.hitCount.toFloat() / hitCount.toFloat())
        }
    }

    def Float projectedConvertCount(StatisticAnalyzerItem champion) {
        return (this.marginOfError(champion) + 1) * convertCount.toFloat()
    }

    def Float confidenceRating(StatisticAnalyzerItem champion) {
        // println "Champion Projected Conversion: ${champion.projectedConvertCount(this)}"
        // println "Contender Projected Conversion: ${this.projectedConvertCount(champion)}"
        return (champion.projectedConvertCount(this) - this.projectedConvertCount(champion)).toFloat().abs()
    }

    def Float confidenceRatio(StatisticAnalyzerItem champion) {
        if (hitCount > champion.hitCount) {
            return (this.confidenceRating(champion) / (hitCount * 2).toFloat())
        } else {
            return (this.confidenceRating(champion) / (champion.hitCount * 2).toFloat())
        }
    }

}
