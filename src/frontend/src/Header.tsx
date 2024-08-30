import { useState } from "react"

interface headerProps {
    onSearch: (data: string) => void
}

function Header({ onSearch }: headerProps) {
    const [id, setId] = useState('')
    const imageUrl = '/logo.png'

    const handleInputChange = (e: any) => {
        setId(e.target.value)
    }

    const sendProps = () => {
        onSearch(id)
    }

    return (
        <div className="header">
            <div className="header-logo">
                <img src={imageUrl} alt="logo" />
                <h2>MAL Grapher</h2>
            </div>
            <div className="header-search">
                <input type="text" value={id} placeholder="Anime ID" onChange={handleInputChange} />
                <button onClick={sendProps}>Search</button>
            </div>
        </div>
    )
}

export default Header