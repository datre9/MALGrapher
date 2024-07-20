import { LineChart } from "@mui/x-charts"
import { useEffect, useState } from "react"

interface Score {
    id: number
    animeId: number
    date: string
    members: number
    rank: number
    score: number
}

function Graph(props: any) {
    const [scores, setScores] = useState<Score[]>(props.scores)
    const [actualScores, setActualScores] = useState<number[]>([])
    const [dates, setDates] = useState<Date[]>([])
    const [ids, setIds] = useState<number[]>([])
    const [members, setMembers] = useState<number[]>([])
    const [ranks, setRanks] = useState<number[]>([])

    useEffect(() => {
        setScores(props.scores)
    }, [props])

    useEffect(() => {
        const addScore = scores.map((score) => score.score)
        setActualScores(addScore)

        const addDate = scores.map((score) => new Date(score.date))
        setDates(addDate)

        const addId = scores.map((score) => score.id)
        setIds(addId)

        const addMember = scores.map((score) => score.members)
        setMembers(addMember)

        const addRank = scores.map((score) => score.rank)
        setRanks(addRank)
    }, [scores])

    const dateNumberFormatter = (date: Date) => date.getFullYear().toString() + '.' + date.getMonth().toString() + '.' + date.getDate().toString()

    return (
        <div>
            <LineChart width={500} height={300}
                series={[{data: members, label: 'Members'}]}
                xAxis={[{scaleType: 'time', data: dates, valueFormatter: dateNumberFormatter}]}/>
        </div>
    )
}

export default Graph