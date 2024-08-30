interface alertProps {
    message: string
    color: string
    onSearch: () => void
}

function Alert(props: alertProps) {
    return (
        <div className="alert">
            <h3 style={{ color: props.color }}>{props.message}</h3>
            <button onClick={() => props.onSearch()}>X</button>
        </div>
    )
}

export default Alert