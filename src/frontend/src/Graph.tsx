import { createTheme, ThemeProvider } from "@mui/material"
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
    const [dates, setDates] = useState<Date[]>([])
    const [selectedArray, setSelectedArray] = useState<any[]>([])

    const [windowWidth, setWindowWidth] = useState(window.innerWidth)

    useEffect(() => {
        window.addEventListener('resize', () => setWindowWidth(window.innerWidth))
        return () => {
            window.removeEventListener("resize", () => setWindowWidth(window.innerWidth))
        }
    }, [windowWidth])

    const selection = props.selection
    useEffect(() => {
        let addArray: any[] = []

        const addDate = props.scores.map((score: Score) => new Date(score.date))
        setDates(addDate)

        switch (selection) {
            case 'score':
                addArray = props.scores.map((score: Score) => score.score)
                break;
            case 'members':
                addArray = props.scores.map((score: Score) => score.members)
                break;
            case 'rank':
                addArray = props.scores.map((score: Score) => score.rank)
                break;
        }

        setSelectedArray(addArray)
    }, [props])

    const dateNumberFormatter = (date: Date) => date.getFullYear().toString() + '.' + date.getMonth().toString() + '.' + date.getDate().toString()

    const theme = createTheme({
        palette: {
            text: {
                primary: '#ffffff',
            },
        }
    })

    return (
        <ThemeProvider theme={theme}>
            <div className="graph">
                <LineChart
                    width={windowWidth - 100}
                    height={400}
                    margin={{ top: 20, right: 20, bottom: 25, left: 70 }}
                    series={[{ data: selectedArray }]}
                    xAxis={[{ scaleType: 'time', data: dates, valueFormatter: dateNumberFormatter }]}
                    yAxis={[{ min: 0, max: selection === 'score' ? 10 : Math.max(...selectedArray) * 1.1, reverse: selection === 'rank' }]}
                    sx={[{ backgroundColor: 'hsl(0, 0%, 5%)' }]}
                    grid={{ horizontal: true, vertical: true }}
                />
            </div>
        </ThemeProvider>
    )
}

export default Graph