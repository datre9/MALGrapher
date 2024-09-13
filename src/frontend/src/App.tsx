import axios from "axios"
import { useEffect, useState } from "react"
import Graph from "./Graph"
import Header from "./Header"
import Selection from "./Selection"
import Alert from "./Alert"

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
    
    const [name, setName] = useState('')
    const [Pname, setPName] = useState('')

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
                setName('')
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
        document.body.scrollTop = 0
        document.documentElement.scrollTop = 0
    }

    const handleSelection = (e: any) => {
        setSelection(e.target.value)
    }

    const handleAlertClose = () => {
        setIsBadRequest(false)
        setName(Pname)
    }

    useEffect(() => {
        setName(animeList.find(x => x.id == id)?.name || name)
        setPName(name)
    }, [id])

    return (
        <div className="app">
            <Header onSearch={handleSearch} />

            <div className="bar">
                <select value={selection} onChange={handleSelection}>
                    <option value="score">Score</option>
                    <option value="members">Members</option>
                    <option value="rank">Rank</option>
                </select>

                <p className="name">{name}</p>

                {isBadRequest ? <Alert message="No records of this anime exist" color="#ff0000" onSearch={handleAlertClose} /> : null}
            </div>

            <Graph scores={scores} selection={selection} />

            <Selection yearChange={handleYearChange} seasonChange={handleSeasonChange} />

            <ul onClick={handleAnimeClick} className="list">{anime}</ul>
        </div>
    )
}

export default App