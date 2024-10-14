import { useState } from "react"

interface selectionProps {
    yearChange: (data: string) => void
    seasonChange: (data: string) => void
}

function Selection({ yearChange, seasonChange }: selectionProps) {
    const d = new Date()

    const getCurrentSeason = () => {
        const seasons = ['winter', 'spring', 'summer', 'fall']
        return seasons[Math.floor(d.getMonth() / 3)]
    }

    const [season, setSeason] = useState(getCurrentSeason())
    const [year, setYear] = useState('')

    const getYears = () => {
        const years = []
        for (let i = d.getFullYear(); i >= 2023; i--) {
            years.push(<option value={i.toString()}>{i}</option>)
        }

        return years
    }

    const handleSeason = (e: any) => {
        setSeason(e.target.value)
        seasonChange(e.target.value)
    }

    const handleYear = (e: any) => {
        setYear(e.target.value)
        yearChange(e.target.value)
    }

    return (
        <div>
            <select value={season} onChange={handleSeason}>
                <option value="winter">Winter</option>
                <option value="spring">Spring</option>
                <option value="summer">Summer</option>
                <option value="fall">Fall</option>
            </select>
            <select value={year} onChange={handleYear}>
                {getYears()}
            </select>
        </div>
    )
}

export default Selection