import axios from "axios"
import { useEffect, useState } from "react"
import Graph from "./Graph"
import Header from "./Header"

interface Score {
    id: number
    animeId: number
    date: string
    members: number
    rank: number
    score: number
}

function App() {
    const [scores, setScores] = useState<Score[]>([])
    const [id, setId] = useState('')
    const [selection, setSelection] = useState<String>()

    const handleSearch = (id: any) => {
        setId(id)
    }

    //TODO: warning message when graph does not update because of bad request
    useEffect(() => {
        axios.get(`http://localhost:8080/get/scores?id=${id}`)
            .then(res => setScores(res.data))
            .catch(err => console.log(err))
    }, [id])

    //TODO: implement selecting different graph data (members, score, ...)
    return (
        <div>
            <Header onSearch={handleSearch}/>
            <Graph scores={scores} selection={selection}/>
        </div>
    );
}

export default App