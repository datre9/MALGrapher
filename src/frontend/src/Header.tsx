import { useState } from "react"

interface headerProps {
    onSearch: (data: string) => void
    onSelection: (data: string) => void
}

function Header({ onSearch, onSelection }: headerProps) {
    const [id, setId] = useState('')
    const [selection, setSelection] = useState('score')

    const handleInputChange = (e: any) => {
        setId(e.target.value)
    }

        const handleSelection = (e: any) => {
        setSelection(e.target.value)
    }

    const sendProps = () => {
        onSearch(id)
        onSelection(selection)
    }

    return (
        <div>
            <select value={selection} onChange={handleSelection}>
                <option value="score">Score</option>
                <option value="members">Members</option>
                <option value="rank">Rank</option>
            </select>
            <input type="text" value={id} placeholder="Anime ID" onChange={handleInputChange} />
            <button onClick={sendProps}>Search</button>
        </div>
    )
}

export default Header