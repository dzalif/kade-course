package com.kucingselfie.kadesubmission.data

import com.kucingselfie.kadesubmission.api.response.DetailTeam
import com.kucingselfie.kadesubmission.model.DetailMatch
import com.kucingselfie.kadesubmission.model.League
import com.kucingselfie.kadesubmission.model.Match
import com.kucingselfie.kadesubmission.model.Search

object FakeRemoteData {
    fun getDummyLeagues() : List<League> {
        val leagues = mutableListOf<League>()
        leagues.add(
            League(
                "4396",
                "English League 1",
                "Football League One (often referred to as League One for short or Sky Bet League 1) is the second-highest division of the Football League and the third tier in the English football league system.\r\n\r\nFootball League One was introduced for the 2004–05 season. It was previously known as the Football League Second Division and prior to the advent of the Premier League, the Football League Third Division.\r\n\r\nAt present (2014–15 season), Oldham Athletic hold the longest tenure in League One, last being out of the division in the 1996–97 season when they were relegated from the Championship. There are currently six former Premier League clubs competing in the League One, namely Barnsley, Bradford City, Coventry City, Oldham Athletic, Sheffield United and Swindon Town.\r\nThere are 24 clubs in Football League One. Each club plays every other club twice (once at home and once away). Three points are awarded for a win, one for a draw and zero for a loss. At the end of the season a table of the final League standings is determined, based on the following criteria in this order: points obtained, goal difference, goals scored, an aggregate of the results between two or more clubs (ranked using the previous three criteria) and, finally, a series of one or more play-off matches.\r\n\r\nAt the end of each season the top two clubs, together with the winner of the play-offs between the clubs which finished in 3rd–6th position, are promoted to Football League Championship and are replaced by the three clubs that finished at the bottom of that division.\r\n\r\nSimilarly, the four clubs that finished at the bottom of Football League One are relegated to Football League Two and are replaced by the top three clubs and the club that won the 4th–7th place play-offs in that division.\r\nSky Sports currently show live League One matches with highlights shown on BBC One on their programme called The Football League Show, which also broadcasts highlights of Football League Championship and Football League Two matches. The show is available on the red button the following Sunday until midday and is available on iPlayer all the following week. Highlights of all games in the Football League are also available to view separately on the Sky Sports website. In Sweden, TV4 Sport has the rights of broadcasting from the league. A couple of league matches during the season of 09\"/10 including play-off matches and the play-off final to the Championship were shown. In Australia, Setanta Sports Australia broadcasts live Championship matches. In the USA and surrounding countries including Cuba, some Football League Championship, Football League One and Football League Two games are shown on beIN Sport.",
                "https:\"/\"/www.thesportsdb.com\"/images\"/media\"/league\"/logo\"/qqxuqq1467458876.png",
                "https:\"/\"/www.thesportsdb.com\"/images\"/media\"/league\"/badge\"/vm1qlp1535986713.png",
                "{strHomeTeam} vs {strAwayTeam}"
            )
        )
        leagues.add(
            League(
                "4397",
                "English League 2",
                "The English Football League Two (often referred to as League Two for short or Sky Bet League Two for sponsorship reasons) is the third and lowest division of the English Football League (EFL) and fourth-highest division overall in the English football league system.\r\n\r\nFootball League Two was introduced for the 2004–05 season. It was previously known as the Football League Third Division. Before the advent of the Premier League, the fourth-highest division was known as the Football League Fourth Division.\r\n\r\nAt present (2018–19 season), Morecambe hold the longest tenure in League Two, last being outside the division in the 2006–07 season when they were promoted from the league then known as the Conference National (now the National League). There are currently two former Premier League clubs competing in League Two, namely Oldham Athletic and Swindon Town.",
                "https:\"/\"/www.thesportsdb.com\"/images\"/media\"/league\"/logo\"/wruwrv1467458767.png",
                "https:\"/\"/www.thesportsdb.com\"/images\"/media\"/league\"/badge\"/v4gft11564832669.png",
                "{strHomeTeam} vs {strAwayTeam}"
            )
        )
        leagues.add(
            League(
                "4329",
                "English League Championship",
                "The Football League Championship (often referred to as the Champion  ship for short, or the Sky Bet Championship for sponsorship reasons) is the second-highest division overall in the English football league system after the Premier League. Each year, the top finishing teams in the Championship are promoted to the Premier League, and the lowest finishing teams are relegated.\r\n\r\nThe Football League Championship, which was introduced for the 2004–05 season, was previously known as the Football League First Division (1992–2004), and before that was known as Division Two (1892–1992). The winners of the Championship receive the Football League Championship trophy, the same trophy as the old First Division champions were handed prior to the Premier League's inception in 1992.\r\n\r\nThe Championship is the wealthiest non-top flight football division in the world and the seventh richest division in Europe. The average match attendance for the 2011–12 season was 17,738, which also makes it the most-watched secondary league in the world.\r\n\r\nIn the 2013–14 season, Leicester City were the division champions, Burnley were the runners up. At present (2014–15 season), Ipswich Town hold the longest tenure in the Championship, last being out of the division in the 2001–02 season when they were relegated from the Premier League.",
                "https:\"/\"/www.thesportsdb.com\"/images\"/media\"/league\"/logo\"/tryvqw1467458861.png",
                "https:\"/\"/www.thesportsdb.com\"/images\"/media\"/league\"/badge\"/m7urjx1535732496.png",
                "{strHomeTeam} vs {strAwayTeam}"
            )
        )
        leagues.add(
            League(
                "4525",
                "English Old 1st Division",
                "During the 1970s and 1980s, the spectre of hooliganism had begun to haunt English football. The Heysel Stadium disaster was the epitome of this, with English hooligans mixing with poor policing and an old stadium to cause the deaths of 39 Juventus fans during the 1985 European Cup final. This led to English teams being banned from European football for five years, and Liverpool - the club involved - being banned for six. Attendances also suffered throughout the league, with hooliganism and the recession being seen as the key factors. Teams in the north of England, the region with some of the worst unemployment rates nationally, suffered a particularly sharp decline in attendances, which did their financial position no favours. Indeed, the mid 1980s saw two former title-winning sides from the north of England - Burnley and Preston North End - relegated to the Fourth Division for the first time, and then come very close to losing their league status completely. In 1986, Wolverhampton Wanderers became only the second team in English football to suffer three successive relegations, dropping into the Fourth Division for the first time as well, although they were saved from closure for the second time in four years by a new owner.\r\n\r\nEven when English teams were re-admitted to European competitions, it was not until 1995 that they regained all of their lost places. And it took a while for English teams to re-establish themselves in Europe. Although Manchester United won the European Cup Winners' Cup in the first season after the ban was lifted, the European Cup was not won by an English club until 1999 – 15 years after the last triumph.\r\n\r\nThe Hillsborough disaster, which also involved Liverpool, though not related to hooliganism but caused by bad policing, an outdated stadium and anti-hooligan fences led to 96 deaths and more than 300 injuries at the FA Cup semi-final in April 1989. These two tragedies led to a modernisation of English football and English grounds by the mid-1990s. Efforts were made to remove hooligans from English football, whilst the Taylor Report led to the grounds of all top level clubs becoming all-seater.\r\n\r\nMatch attendances, which had been in decline since the late 1970s, were beginning to recover by the turn of end of the 1980s thanks to the improving image of football as well as the strengthened national economy and falling unemployment after the crises of the 1970s and the first half of the 1980s.\r\n\r\nOn the field, Liverpool's domination was coming to an end by 1991. One of the biggest success stories of this era was that of Wimbledon, who rose from the Fourth Division to the First in just four seasons, before finishing sixth in their inaugural season in the top flight and beating Liverpool 1–0 in the 1988 FA Cup final, one of the competition's biggest shocks. They had only joined the league in 1977. Another team to make an improbably quick rise from Fourth to First Divisions was Swansea City, who had climbed three divisions between 1977 and 1981. They finished sixth in their first top division campaign, but were relegated the following year and in 1986 fell back into the Fourth Division, having narrowly avoided going out of business. Watford had reached the First Division for the first time in 1982 and finished league runners-up in their first season at this level and were FA Cup runners-up a year later, but were relegated in 1988.\r\n\r\nA number of other small clubs achieved success at this time. Charlton Athletic, who were forced to leave The Valley and ground-share with West Ham for safety reasons in 1985, won promotion to the First Division in 1986 after an exile of nearly 30 years. They defied the odds by surviving at this level for four seasons. Norwich City enjoyed even more success during this era. The Norfolk club went down to the Second Division in 1985 but that blow was cushioned by a League Cup triumph. They returned to the top flight a year later and finished fifth on their comeback, also coming fourth and reaching the FA Cup semi-finals in 1989, being in with a serious chance of winning the double with only a few weeks of the season remaining. They reached another FA Cup semi-final in 1992. Oxford United, who had only joined the Football League in 1962, reached the First Division in 1985 and lifted the League Cup the following season. They went back down again in 1988, the same year that Middlesbrough reached the First Division a mere two seasons after almost going out of business as a Third Division side. Luton Town, who began the latest of several spells as a First Division side in 1982, won the Football League Cup - their first major trophy - in 1988 at the expense of a much more fancied Arsenal side.\r\n\r\nOne fallen giant to enjoy something of a resurgence in this era was Derby County. They had been relegated to the Third Division in 1984, just nine years after bei ng league champions, but back-to-back promotions saw them back in the First Division in 1987. They emerged as surprise title contenders in 1988–89 and finished fifth, only missing out on a UEFA Cup place due to the ban on English clubs in European competition. But Derby were unable to sustain their run of success, and went down to the Second Division in 1991.\r\n\r\nAfter their three consecutive relegations and almost going out of business twice in four years, Wolverhampton Wanderers were beginning to recover by 1987. By 1989, they had won promotion to the Second Division almost single-handedly thanks to the goalscoring exploits of striker Steve Bull, who became the first English footballer to score 50 or more competitive goals in successive seasons, and one of the few Third Division players to be selected for the senior England team. Local businessman Jack Hayward took the club over in 1990, and declared his ambition to restore Wolves to the elite on English football.\r\n\r\nBolton Wanderers, four times FA Cup winners, were relegated to the Fourth Division in 1987, the same year that Sunderland fell into the Third Division for the first time in their history. Both teams, however, won promotion at the first attempt. Sunderland returned to the First Division in 1990 but went down after just one season.\r\n\r\nBurnley's recovery was more steady; they did not climb out of the league's basement division until 1992 and did not reclaim their top flight status until 2009, only surviving for one season at this level.\r\n\r\nWith Liverpool's fortunes waning, George Graham's Arsenal emerged as a dominant force in the English game, winning the League Cup in 1987 and two league titles, in 1989 and 1991, the former being won in the final minute of the final game of the season against title rivals Liverpool, with young midfielder Michael Thomas scoring the crucial goal. Arsenal would go on to be the first side to pick up the Cup Double in 1993, and followed it with a Cup Winners' Cup the year after. The 1991 title triumph was achieved with just one defeat from 38 league games.\r\n\r\nArsenal's neighbours Tottenham were also successful, winning the FA Cup in 1990–91, with midfielder Paul Gascoigne proving the hero in the semi-finals against Arsenal before injuring himself in the final against Nottingham Forest. Tottenham bought Barcelona's high-scoring England striker Gary Lineker in 1989, and he continued his excellent form over three years at the club before leaving to finish his career in Japan.\r\n\r\nLeeds had finally won promotion back to the top flight in 1990 and under Howard Wilkinson they won the 1991–92 league title. Wilkinson is still the most recent English manager to win the league championship. However, the departure of Eric Cantona to Manchester United, amongst other factors, meant they were unable to make a regular challenge for the title following the creation of the Premier League, although they did survive at this level for 12 seasons and achieved regular top five finishes.\r\n\r\nManchester United's six-year trophyless run had ended in 1983 when manager Ron Atkinson (appointed in 1981) guided them to FA Cup glory. They achieved another triumph two years later, but had still gone without a league title since 1967. 10 successive league wins at the start of the 1985–86 season suggested that the title was on its way back to Old Trafford, but United's form fell away as they finished fourth and Liverpool sealed the title. A terrible start to the 1986–87 season cost Atkinson his job in early November, when Alex Ferguson was recruited from Aberdeen. Ferguson strengthened the squad in the 1987 close season and the first stages of the new season and things were looking good as Ferguson's first full season as manager saw United finished second behind runaway champions Liverpool. Further signings after this improvement suggested that the title was even closer for United, but a series of injuries blighted the side and they finished 11th in 1989. United's wait for silverware ended in 1990 when they won their 7th FA Cup, and a year later they won the European Cup Winners' Cup, but it had now been well over 20 years since the league title had been United's.\r\n\r\nDespite failure to qualify for Euro 1984 (the first major tournament since the appointment of Bobby Robson as manager), England continued to improve as the 1980s wore on, losing controversially to Argentina in the 1986 World Cup and unluckily on penalties to Germany in the semi-finals of the 1990 World Cup, eventually finishing fourth. This success for the national team, and the gradually improving grounds, helped to reinvigorate football's popularity. Attendances rose from the late 1980s and continued to do so as football moved into the business era.\r\n\r\nHowever, the ban on English clubs in European competitions from 1985 to 1990 had led to many English-based players moving overseas. Manchester United striker Mark Hughes and Everton's top scorer Gary Lineker were sold to FC Barcelona in 1986, although both players were back in England by the decade's end, Hughes back at Old Trafford and Lineker playing for Tottenham. Ian Rush left Liverpool for Juventus in 1987, but returned to Anfield the following year. Chris Waddle left Tottenham for Marseille in 1989 and stayed there for three years before returning to England to sign for Sheffield Wednesday. After being appointed Rangers manager in 1986, former Liverpool player Graeme Souness signed a host of English-based players for the Ibrox club.\r\n\r\nEven after the ban on English clubs in Europe was lifted, a number of high-profile players moved overseas. Gary Lineker opted to complete his playing career in Japan on leaving Tottenham in 1992, the same year that Paul Gascoigne moved to Italy in a lucrative transfer to Lazio. Another of England's 1990 World Cup stars, David Platt, had been sold by Aston Villa to Italian side Bari in 1991, later playing for Juventus and Sampdoria before returning to England in 1995 to sign for Arsenal - the same year that Gascoigne left Lazio to sign for Rangers in Scotland.\r\n\r\nThe late 1980s and early 1990s saw the emergence of numerous young players who went on to reach great heights in the game. These include Paul Gascoigne, David Platt, Matt Le Tissier, Lee Sharpe, Ryan Giggs and Paul Merson.\r\n\r\nEstablished great players who were still playing the top in the early 1990s include Ian Rush, Peter Beardsley, Bryan Robson, Steve Bruce, Neville Southall and Ray Wilkins.\r\n\r\nThis era also saw many famous names hanging up their boots after long and illustrious careers. These include Ray Clemence, Gary Bailey, Alan Hansen, Craig Johnston, Norman Whiteside, Andy Gray and Billy Bonds.\r\n\r\nSuccessful managers of this era include Kenny Dalglish, George Graham, Howard Kendall, Howard Wilkinson, Alex Ferguson, Bobby Gould, John Lyall, Jim Smith, Maurice Evans and Dave Bassett.",
                null,
                null,
                "{strHomeTeam} vs {strAwayTeam}"
            )
        )
        leagues.add(
            League(
                "4328",
                "English Premier League",
                "The Premier League (often referred to as the English Premier League (EPL) outside England), is the top level of the English football league system. Contested by 20 clubs, it operates on a system of promotion and relegation with the English Football League (EFL).\r\n\r\nThe Premier League is a corporation in which the member clubs act as shareholders. Seasons run from August to May with each team playing 38 matches (playing each other home and away). Most games are played on Saturday and Sunday afternoons. The Premier League has featured 47 English and two Welsh clubs since its inception, making it a cross-border league.\r\n\r\nThe competition was formed as the FA Premier League on 20 February 1992 following the decision of clubs in the Football League First Division to break away from the Football League, founded in 1888, and take advantage of a lucrative television rights deal. The deal was worth £1 billion a year domestically as of 2013–14, with BSkyB and BT Group securing the domestic rights to broadcast 116 and 38 games respectively. The league generates €2.2 billion per year in domestic and international television rights. In 2014–15, teams were apportioned revenues of £1.6 billion, rising sharply to £2.4 billion in 2016–17.\r\n\r\nThe Premier League is the most-watched sports league in the world, broadcast in 212 territories to 643 million homes and a potential TV audience of 4.7 billion people. In the 2014–15 season, the average Premier League match attendance exceeded 36,000, second highest of any professional football league behind the Bundesliga's 43,500. Most stadium occupancies are near capacity. The Premier League ranks second in the UEFA coefficients of leagues based on performances in European competitions over the past five seasons, as of 2018.\r\n\r\nForty-nine clubs have competed since the inception of the Premier League in 1992. Six of them have won the title: Manchester United (13), Chelsea (5), Arsenal (3), Manchester City (3), Blackburn Rovers (1), and Leicester City (1). Following the 2003–04 season, Arsenal acquired the nickname \"The Invincibles\" as they became, and still remain, the only club to complete a Premier League campaign without losing a single game. The record of most points in a season is 100 by Manchester City in 2017–18.",
                "https:\"/\"/www.thesportsdb.com\"/images\"/media\"/league\"/logo\"/4c377s1535214890.png",
                "https:\"/\"/www.thesportsdb.com\"/images\"/media\"/league\"/badge\"/i6o0kh1549879062.png",
                "{strHomeTeam} vs {strAwayTeam}"
            )
        )
        leagues.add(
            League(
                "4526",
                "Football League Super Cup",
                "The Football League Super Cup (known for sponsorship reasons as the ScreenSport Super Cup) was a one-off football club competition held in England in the 1985–86 season. It was organised by the Football League and was intended as a form of financial and sporting compensation for the English clubs which had qualified for European competition in the previous season but had been banned from entering European tournaments by UEFA following the Heysel Stadium disaster. With the ban set to last into the foreseeable future, England's clubs stood to lose a great deal of revenue, and would also have fewer opportunities to win silverware, so the Super Cup was established in order to hopefully offset at least some of this lost income, as well as offering additional competition for them.\r\n\r\nThe Football League's original intention was to hold the Super Cup annually t the Football League initially failed to attract any form of sponsorship for it. Cable TV sports channel Screensport agreed to sponsor the tournament's final in September 1986.\r\n\r\nMartin Edwards, the Chairman of Manchester United, wrote in his programme notes for United's opening group match against Everton that he hoped that the Super Cup would \"only last for one season\", meaning that he hoped that the UEFA ban on English clubs would only last for that long. Its rated so low by United that it isn't mentioned at all on club's official statistic site.  The cup's demise was indeed swift, but that had nothing to do with any relaxation of the ban, which eventually lasted until 1990. As some indication of how the clubs felt about the ignominy of the situation, Howard Kendall recalled that, prior to his Everton side's group match at Norwich City, he sent his team out with the following team-talk: \"What a waste of time this is – out you go.\"",
            null,
                null,
                "{strHomeTeam} vs {strAwayTeam}")
        )
        return leagues
    }

    fun getDummyDetailLeague() : List<League> {
        val leagues = mutableListOf<League>()
        leagues.add(
            League(
                "4396",
                "English League 1",
                "Football League One (often referred to as League One for short or Sky Bet League 1) is the second-highest division of the Football League and the third tier in the English football league system.\r\n\r\nFootball League One was introduced for the 2004–05 season. It was previously known as the Football League Second Division and prior to the advent of the Premier League, the Football League Third Division.\r\n\r\nAt present (2014–15 season), Oldham Athletic hold the longest tenure in League One, last being out of the division in the 1996–97 season when they were relegated from the Championship. There are currently six former Premier League clubs competing in the League One, namely Barnsley, Bradford City, Coventry City, Oldham Athletic, Sheffield United and Swindon Town.\r\nThere are 24 clubs in Football League One. Each club plays every other club twice (once at home and once away). Three points are awarded for a win, one for a draw and zero for a loss. At the end of the season a table of the final League standings is determined, based on the following criteria in this order: points obtained, goal difference, goals scored, an aggregate of the results between two or more clubs (ranked using the previous three criteria) and, finally, a series of one or more play-off matches.\r\n\r\nAt the end of each season the top two clubs, together with the winner of the play-offs between the clubs which finished in 3rd–6th position, are promoted to Football League Championship and are replaced by the three clubs that finished at the bottom of that division.\r\n\r\nSimilarly, the four clubs that finished at the bottom of Football League One are relegated to Football League Two and are replaced by the top three clubs and the club that won the 4th–7th place play-offs in that division.\r\nSky Sports currently show live League One matches with highlights shown on BBC One on their programme called The Football League Show, which also broadcasts highlights of Football League Championship and Football League Two matches. The show is available on the red button the following Sunday until midday and is available on iPlayer all the following week. Highlights of all games in the Football League are also available to view separately on the Sky Sports website. In Sweden, TV4 Sport has the rights of broadcasting from the league. A couple of league matches during the season of 09\"/10 including play-off matches and the play-off final to the Championship were shown. In Australia, Setanta Sports Australia broadcasts live Championship matches. In the USA and surrounding countries including Cuba, some Football League Championship, Football League One and Football League Two games are shown on beIN Sport.",
                "https:\"/\"/www.thesportsdb.com\"/images\"/media\"/league\"/logo\"/qqxuqq1467458876.png",
                "https:\"/\"/www.thesportsdb.com\"/images\"/media\"/league\"/badge\"/vm1qlp1535986713.png",
                "{strHomeTeam} vs {strAwayTeam}"
            )
        )
        return leagues
    }

    fun getDummySearchResult() : List<Search> {
        val results = mutableListOf<Search>()
        results.add(
            Search(
                "673198",
                "4482",
                "Arsenal vs Leeds",
                "FA Cup",
                "2020-01-06",
                "Arsenal",
                "Leeds",
                null,
                null,
                null
            )
        )
        results.add(
            Search(
                "672072",
                "4481",
                "Standard vs Arsenal",
                "UEFA Europa League",
                "2019-12-12",
                "Standard",
                "Arsenal",
                "2",
                "2",
                null
            )
        )
        results.add(
            Search(
                "671881",
                "4355",
                "PFC Sochi vs Arsenal Tula",
                "Russian Football Premier League",
                "2020-03-01",
                "PFC Sochi",
                "Arsenal Tula",
                null,
                null,
                null
            )
        )
        results.add(
            Search(
                "671857",
                "4355",
                "Arsenal Tula vs Lok. Moscow",
                "Russian Football Premier League",
                "2019-12-06",
                "Arsenal Tula",
                "Lok. Moscow",
                "4",
                "0",
                null
            )
        )
        results.add(
            Search(
                "671386",
                "4481",
                "Arsenal vs Ein Frankfurt",
                "UEFA Europa League",
                "2019-11-28",
                "Arsenal",
                "Ein Frankfurt",
                "1",
                "2",
                null
            )
        )
        results.add(
            Search(
                "652848",
                "4355",
                "Spartak Moscow vs Arsenal Tula",
                "Russian Football Premier League",
                "2019-11-04",
                "Spartak Moscow",
                "Arsenal Tula",
                "0",
                "1",
                null
            )
        )
        results.add(
            Search(
                "652847",
                "4481",
                "Guimaraes vs Arsenal",
                "UEFA Europa League",
                "2019-11-06",
                "Guimaraes",
                "Arsenal",
                "1",
                "1",
                null
            )
        )
        results.add(
            Search(
                "649351",
                "4570",
                "Liverpool vs Arsenal",
                "EFL Cup",
                "2019-10-30",
                "Liverpool",
                "Arsenal",
                "5",
                "5",
                null
            )
        )
        results.add(
            Search(
                "637049",
                "4481",
                "Arsenal vs Guimaraes",
                "UEFA Europa League",
                "2019-10-24",
                "Arsenal",
                "Guimaraes",
                "3",
                "2",
                null
            )
        )
        results.add(
            Search(
                "619328",
                "4481",
                "Arsenal vs Standard",
                "UEFA Europa League",
                "2019-10-03",
                "Arsenal",
                "Standard",
                "4",
                "0",
                null
            )
        )
        results.add(
            Search(
                "613315",
                "4570",
                "Arsenal vs Nottingham F.",
                "EFL Cup",
                "2019-09-24",
                "Arsenal",
                "Nottingham F.",
                "5",
                "0",
                null
            )
        )
        results.add(
            Search(
                "612996",
                "4481",
                "Ein Frankfurt vs Arsenal",
                "UEFA Europa League",
                "2019-09-19",
                "Ein Frankfurt",
                "Arsenal",
                "0",
                "3",
                null
            )
        )
        results.add(
            Search(
                "610898",
                "4355",
                "Arsenal Tula vs FC Ufa",
                "Russian Football Premier League ",
                "2019-08-11",
                "Arsenal Tula",
                "FC Ufa",
                "1",
                "0",
                null
            )
        )
        results.add(
            Search(
                "610505",
                "4571",
                "Arsenal vs Man City",
                "FA Community Shield",
                "2014-08-10",
                "Arsenal",
                "Man City",
                "3",
                "0",
                null
            )
        )
        results.add(
            Search(
                "610504",
                "4571",
                "Arsenal vs Chelsea",
                "FA Community Shield",
                "2015-08-02",
                "Arsenal",
                "Chelsea",
                "1",
                "0",
                null
            )
        )
        results.add(
            Search(
                "610502",
                "4571",
                "Arsenal vs Chelsea",
                "FA Community Shield",
                "2017-08-06",
                "Arsenal",
                "Chelsea",
                "1",
                "1",
                null
            )
        )
        results.add(
            Search(
                "610401",
                "4569",
                "Barcelona vs Arsenal",
                "Club Friendlies",
                "2019-08-04",
                "Barcelona",
                "Arsenal",
                "2",
                "1",
                null
            )
        )
        results.add(
            Search(
                "610398",
                "4355",
                "FC Tambov vs Arsenal Tula",
                "Russian Football Premier League ",
                "2019-08-04",
                "FC Tambov",
                "Arsenal Tula",
                "0",
                "1",
                null
            )
        )
        results.add(
            Search(
                "609258",
                "4569",
                "Angers vs Arsenal",
                "Club Friendlies",
                "2019-07-31",
                "Angers",
                "Arsenal",
                "1",
                "1",
                "https://www.thesportsdb.com/images/media/event/thumb/aealqn1564598018.jpg"
            )
        )
        results.add(
            Search(
                "608262",
                "4505",
                "Arsenal vs Fiorentina",
                "International Champions Cup",
                "2019-07-20",
                "Arsenal",
                "Fiorentina",
                "3",
                "0",
                "https://www.thesportsdb.com/images/media/event/thumb/22q10c1563628253.jpg"
            )
        )
        results.add(
            Search(
                "608216",
                "4355",
                "Krylya Sovetov Samara vs Arsenal Tula",
                "Russian Football Premier League ",
                "2019-07-20",
                "Krylya Sovetov Samara",
                "Arsenal Tula",
                "2",
                "3",
                null
            )
        )
        results.add(
            Search(
                "607290",
                "4355",
                "Arsenal Tula vs Dinamo Moscow",
                "Russian Football Premier League",
                "2019-07-12",
                "Arsenal Tula",
                "Dinamo Moscow",
                "1",
                "1",
                null
            )
        )
        results.add(
            Search(
                "603468",
                "4569",
                "Boreham Wood vs Arsenal",
                "Club Friendlies",
                "2019-07-06",
                "Boreham Wood",
                "Arsenal",
                "3",
                "3",
                null
            )
        )
        results.add(
            Search(
                "602502",
                "4328",
                "Arsenal vs Watford",
                "English Premier League",
                "2020-05-17",
                "Arsenal",
                "Watford",
                null,
                null,
                null
            )
        )
        results.add(
            Search(
                "602493",
                "4328",
                "Aston Villa vs Arsenal",
                "English Premier League",
                "2020-05-09",
                "Aston Villa",
                "Arsenal",
                null,
                null,
                null
            )
        )
        return results
    }

    fun getDummyNextMatch() : List<Match> {
        val matches = mutableListOf<Match>()
        matches.add(
            Match(
                "605171",
                "Doncaster vs Shrewsbury",
                null,
                "133620",
                "134377"
            )
        )
        matches.add(
            Match(
                "605272",
                "Sunderland vs Wycombe",
                null,
                "133603",
                "134382"
            )
        )
        matches.add(
            Match(
                "605273",
                "Oxford Utd vs Rotherham",
                null,
                "134361",
                "134231"
            )
        )
        matches.add(
            Match(
                "605274",
                "Peterboro vs Gillingham",
                null,
                "133631",
                "134230"
            )
        )
        matches.add(
            Match(
                "605275",
                "Blackpool vs Bury",
                null,
                "133618",
                "134359"
            )
        )
        matches.add(
            Match(
                "605276",
                "Portsmouth vs Wimbledon",
                null,
                "133629",
                "134241"
            )
        )
        matches.add(
            Match(
                "605277",
                "Bristol Rovers vs Doncaster",
                null,
                "134358",
                "133620"
            )
        )
        matches.add(
            Match(
                "605278",
                "Rochdale vs Bolton",
                null,
                "134364",
                "133606"
            )
        )
        matches.add(
            Match(
                "605279",
                "Burton vs Fleetwood Town",
                null,
                "134376",
                "134374"
            )
        )
        matches.add(
            Match(
                "605280",
                "Shrewsbury vs Lincoln",
                null,
                "134377",
                "135900"
            )
        )
        matches.add(
            Match(
                "605281",
                "Coventry vs Milton Keynes Dons",
                null,
                "133625",
                "134371"
            )
        )
        matches.add(
            Match(
                "605282",
                "Southend vs Tranmere",
                null,
                "134209",
                "134267"
            )
        )
        matches.add(
            Match(
                "605283",
                "Ipswich vs Accrington",
                null,
                "133622",
                "134368"
            )
        )
        matches.add(
            Match(
                "605147",
                "Lincoln vs Bolton",
                null,
                "135900",
                "133606"
            )
        )
        matches.add(
            Match(
                "605174",
                "Oxford Utd vs Ipswich",
                null,
                "134361",
                "133622"
            )
        )
        return matches
    }

    fun getDummyPreviousMatch() : List<Match> {
        val matches = mutableListOf<Match>()
        matches.add(
            Match(
                "605271",
                "Southend vs Gillingham",
                null,
                "134209",
                "134230"
            )
        )
        matches.add(
            Match(
                "605270",
                "Coventry vs Rotherham",
                null,
                "133625",
                "134231"
            )
        )
        matches.add(
            Match(
                "605269",
                "Shrewsbury vs Tranmere",
                null,
                "134377",
                "134267"
            )
        )
        matches.add(
            Match(
                "605268",
                "Burton vs Milton Keynes Dons",
                null,
                "134376",
                "134371"
            )
        )
        matches.add(
            Match(
                "605267",
                "Rochdale vs Wimbledon",
                null,
                "134364",
                "134241"
            )
        )
        matches.add(
            Match(
                "605266",
                "Bristol Rovers vs Bury",
                null,
                "134358",
                "134359"
            )
        )
        matches.add(
            Match(
                "605265",
                "Portsmouth vs Doncaster",
                null,
                "133629",
                "133620"
            )
        )
        matches.add(
            Match(
                "605263",
                "Peterboro vs Wycombe",
                null,
                "133631",
                "134382"
            )
        )
        matches.add(
            Match(
                "605262",
                "Oxford Utd vs Accrington",
                null,
                "134361",
                "134368"
            )
        )
        matches.add(
            Match(
                "605261",
                "Sunderland vs Lincoln",
                null,
                "133603",
                "135900"
            )
        )
        matches.add(
            Match(
                "605260",
                "Ipswich vs Fleetwood Town",
                null,
                "133622",
                "134374"
            )
        )
        matches.add(
            Match(
                "605259",
                "Rotherham vs Blackpool",
                null,
                "134231",
                "133618"
            )
        )
        matches.add(
            Match(
                "605258",
                "Bolton vs Burton",
                null,
                "133606",
                "134376"
            )
        )
        matches.add(
            Match(
                "605257",
                "Milton Keynes Dons vs Bristol Rovers",
                null,
                "134371",
                "134358"
            )
        )
        matches.add(
            Match(
                "605256",
                "Wimbledon vs Southend",
                null,
                "134241",
                "134209"
            )
        )
        return matches
    }

    fun getDummyDetailMatch() : List<DetailMatch> {
        val detailMatch = mutableListOf<DetailMatch>()
        detailMatch.add(
            DetailMatch(
                "2020-01-07",
                "605171",
                "Doncaster vs Shrewsbury",
                "0",
                "0",
                "Shrewsbury",
                "Doncaster",
                "19:45:00",
                null,
                null,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
            )
        )
        return detailMatch
    }

    fun getDummyDetailTeamHome() : List<DetailTeam> {
        val detailTeam = mutableListOf<DetailTeam>()
        detailTeam.add(
            DetailTeam(
                "133620",
                "https://www.thesportsdb.com/images/media/team/badge/ssvpvx1424033260.png"
            )
        )
        return detailTeam
    }

    fun getDummyDetailTeamAway() : List<DetailTeam> {
        val detailTeam = mutableListOf<DetailTeam>()
        detailTeam.add(
            DetailTeam(
                "134377",
                "https://www.thesportsdb.com/images/media/team/badge/9zqhpw1546434997.png"
            )
        )
        return detailTeam
    }
}