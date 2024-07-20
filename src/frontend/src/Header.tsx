import { useState } from "react"

interface headerProps {
    onSearch: (data: string) => void
}

function Header({ onSearch }: headerProps) {
    const [id, setId] = useState('')

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setId(e.target.value)
    }

    const sendId = () => {
        onSearch(id)
    }

    return (
        <div>
            <input type="text" value={id} placeholder="Anime ID" onChange={handleInputChange}/>
            <button onClick={sendId}>Search</button>
        </div>
    )
}

export default Header