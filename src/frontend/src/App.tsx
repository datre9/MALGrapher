import axios from "axios"
import { useEffect, useState } from "react"
import Graph from "./Graph"
import Header from "./Header"
import { Alert } from "@mui/material"

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
    const [badIsRequest, setIsBadRequest] = useState(false)

    const handleSearch = (id: any) => {
        setId(id)
    }

    const handleSelection = (s: any) => {
        setSelection(s)
    }

    useEffect(() => {
        setIsBadRequest(false)
        if (id === '') return
        axios.get(`http://localhost:8080/get/scores?id=${id}`)
            .then(res => setScores(res.data))
            .catch(err => {
                console.log(err)
                setIsBadRequest(true)
            })
    }, [id])

    return (
        <div>
            <Header onSearch={handleSearch} onSelection={handleSelection}/>
            {badIsRequest ? <Alert severity="error">No records for this anime or anime does not exist</Alert> : null}
            <Graph scores={scores} selection={selection}/>
        </div>
    );
}

export default App