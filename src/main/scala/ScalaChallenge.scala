import scala.annotation.tailrec
import scala.io.StdIn
import scala.util.control.Breaks._
import scala.util.Random

object ScalaChallenge {
  def main(args: Array[String]): Unit = {
    println("🌟 Welcome to the Football Betting Game! 🌟")
    val teams = List("Colombia", "Ecuador", "Argentina", "Germany", "United States", "Brazil", "France", "China")
    println(s"The list of countries is: $teams\n")
    breakable {
      while (true) {
        println("Which country would you like to bet on? 🌎")
        print("Country: ")
        val myCountry = StdIn.readLine()
        if (teams.contains(myCountry)) {
          println("Let's begin\n")
          Thread.sleep(1000)
          val randomTeams = Random.shuffle(teams)
          val winnerTeam = tournament(randomTeams, 0).head
          println(s"The Champion is $winnerTeam 🏆\n")
          if (winnerTeam == myCountry) {
            println(s"\nCongratulations! You bet on $winnerTeam was successful! 🎉")
          } else {
            println(s"\nOops! You bet on $myCountry was unsuccessful. Better luck next time! 😔")
          }
          break()
        } else {
          println("\nThe country is not in the list. Please enter another one. ❌\n")
        }
      }
    }
  }

  @tailrec
  def tournament(teams: List[String], counter: Int): List[String] = {
    if (counter == 0) {
      println("\nQuarterfinals:\n")
      Thread.sleep(2000)
    } else if (counter == 1) {
      println("\nSemifinals:\n")
      Thread.sleep(2000)
    } else if (counter == 2) {
      println("\nFinal:\n")
      Thread.sleep(2000)
    }
    if (teams.isEmpty) {
      throw new IllegalArgumentException("The list of teams cannot be empty")
    } else if (teams.length == 1) {
      teams
    } else if (teams.length > 1) {
      var winners: List[String] = List()
      for (i <- 0 until teams.length by 2) {
        if (i + 1 < teams.length) {
          var team1 = teams(i)
          var team2 = teams(i + 1)
          var goalsTeam1 = Random.nextInt(6)
          var goalsTeam2 = Random.nextInt(6)
          if (goalsTeam1 == goalsTeam2) {
            println(s"$team1 vs $team2 was a draw")
            println(s"Let's go to penalties shootout \n")
            Thread.sleep(2000)
            println(s"$team1 vs $team2 Penalties\n")
            var penalties1 = Random.nextInt(6)
            var penalties2 = Random.nextInt(6)
            if (penalties1 > penalties2) {
              println(s"$team1 $penalties1 - $penalties2 $team2\n")
              println(s"$team1 won penalties! \n")
              winners = winners :+ team1
              Thread.sleep(2000)
            } else if (penalties1 < penalties2) {
              println(s"$team1 $penalties1 - $penalties2 $team2\n")
              println(s"$team2 won penalties! \n")
              winners = winners :+ team2
              Thread.sleep(2000)
            } else {
              println(s"$team1 $penalties1 - $penalties2 $team2")
              println("Penalty shootout draw, continuing penalties... ⚽\n")
              Thread.sleep(2000)
              breakable {
                while (true) {

                  var teamOneGoal = Random.nextInt(2)
                  if (teamOneGoal == 1) {
                    println(s"$team1 penalty shootout streak")
                    penalties1 += 1
                    Thread.sleep(2000)
                  }
                  else{
                    println(s"$team1 penalty does not shootout streak")
                    Thread.sleep(2000)
                  }


                  var teamTwoGoal = Random.nextInt(2)
                  if (teamTwoGoal == 1) {
                    println(s"$team2 penalty shootout streak")
                    penalties2 += 1
                    Thread.sleep(2000)
                  }
                  else
                    {
                      println(s"$team2 penalty does not shootout streak")
                      Thread.sleep(2000)
                    }
                  if (teamTwoGoal == teamOneGoal) {
                    println(s"$team1 $penalties1 - $penalties2 $team2")
                    println("Penalty shootout draw, continuing penalties... ⚽\n")
                  } else if (teamTwoGoal > teamOneGoal) {
                    println(s"$team1 $penalties1 - $penalties2 $team2\n")
                    println(s"$team2 won penalties! ")
                    winners = winners :+ team2
                    Thread.sleep(2000)
                    break()
                  } else {
                    println(s"$team1 $penalties1 - $penalties2 $team2\n")
                    println(s"$team1 won penalties! ")
                    winners = winners :+ team1
                    Thread.sleep(2000)
                    break()
                  }
                }
                }

            }
          } else if (goalsTeam1 > goalsTeam2) {
            println(s"$team1 $goalsTeam1 - $goalsTeam2 $team2")
            println(s"$team1 won the game! ⚽\n")
            winners = winners :+ team1
            Thread.sleep(2000)
          } else {
            println(s"$team1 $goalsTeam1 - $goalsTeam2 $team2")
            println(s"$team2 won the game! ⚽\n")
            winners = winners :+ team2
            Thread.sleep(2000)
          }
        }
      }
      Thread.sleep(2000)
      tournament(winners, counter + 1)
    } else {
      List.empty[String] // Default case, should never reach here
    }
  }
}
