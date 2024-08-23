import axios from "axios"
import { useEffect, useState } from "react"
import Graph from "./Graph"
import Header from "./Header"
import { Alert } from "@mui/material"
import Selection from "./Selection"

interface Score {
    id: number
    animeId: number
    date: string
    members: number
    rank: number
    score: number
}

interface Anime {
    id: string
    name: string
    url: string
    season: string
    year: number
}

function App() {
    const [scores, setScores] = useState<Score[]>([])

    const [id, setId] = useState('')
    const [selection, setSelection] = useState('score')

    const [isBadRequest, setIsBadRequest] = useState(false)

    const [year, setYear] = useState('2024')
    const [season, setSeason] = useState('summer')
    const [animeList, setAnimeList] = useState<Anime[]>([])

    const anime = animeList.map(x => <li key={x.id} id={x.id}>{x.name}</li>)

    const handleYearChange = (year: any) => {
        setYear(year)
    }

    const handleSeasonChange = (season: any) => {
        setSeason(season)
    }

    useEffect(() => {
        setIsBadRequest(false)
        if (id === '') return
        axios.get(`http://localhost:8080/get/scores?id=${id}`)
            .then(res => {
                setScores(res.data)
            })
            .catch(err => {
                console.log(err)
                setIsBadRequest(true)
            })
    }, [id])

    useEffect(() => {
        axios.get(`http://localhost:8080/get/popular?season=${season}&year=${year}`)
            .then(res => setAnimeList(res.data))
            .catch(err => {
                console.log(err)
            })
    }, [year, season])

    const handleSearch = (id: any) => {
        setId(id)
    }

    const handleAnimeClick = (e: any) => {
        setId(e.target.id)
    }

    const handleSelection = (e: any) => {
        setSelection(e.target.value)
    }

    return (
        <div>
            <Header onSearch={handleSearch} />
            {isBadRequest ? <Alert severity="error">No records for this anime or anime does not exist</Alert> : null}

            <select value={selection} onChange={handleSelection}>
                <option value="score">Score</option>
                <option value="members">Members</option>
                <option value="rank">Rank</option>
            </select>

            <Graph scores={scores} selection={selection}/>

            <Selection yearChange={handleYearChange} seasonChange={handleSeasonChange} />

            <ol onClick={handleAnimeClick}>{anime}</ol>
        </div>
    )
}

export default App