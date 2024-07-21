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
    const [dates, setDates] = useState<Date[]>([])
    const [selectedArray, setSelectedArray] = useState<any[]>([])
    const [graphLabel, setGraphLabel] = useState('')

    useEffect(() => {
        setScores(props.scores)

        const selection = props.selection
        let addArray: any[] = []

        const addDate = scores.map((score) => new Date(score.date))
        setDates(addDate)

        switch (selection) {
            case 'score':
                addArray = scores.map((score) => score.score)
                setGraphLabel('Score')
                break;
            case 'members':
                addArray = scores.map((score) => score.members)
                setGraphLabel('Member')
                break;
            case 'rank':
                addArray = scores.map((score) => score.rank)
                setGraphLabel('Rank')
                break;
        }

        setSelectedArray(addArray)
    }, [props])

    const dateNumberFormatter = (date: Date) => date.getFullYear().toString() + '.' + date.getMonth().toString() + '.' + date.getDate().toString()

    return (
        <div>
            <LineChart width={500} height={300}
                series={[{data: selectedArray, label: graphLabel}]}
                xAxis={[{scaleType: 'time', data: dates, valueFormatter: dateNumberFormatter}]}/>
        </div>
    )
}

export default Graph