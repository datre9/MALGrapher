import { useState } from "react"

interface headerProps {
    onSearch: (data: string) => void
}

function Header({ onSearch }: headerProps) {
    const [id, setId] = useState('')

    const handleInputChange = (e: any) => {
        setId(e.target.value)
    }

    const sendProps = () => {
        onSearch(id)
    }

    return (
        <div>
            <input type="text" value={id} placeholder="Anime ID" onChange={handleInputChange} />
            <button onClick={sendProps}>Search</button>
        </div>
    )
}

export default Header