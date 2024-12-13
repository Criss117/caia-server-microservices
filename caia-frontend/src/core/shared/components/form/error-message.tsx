import React from "react";

interface Props {
  error?: string | null | undefined;
}

const ErrorMessage = ({ error }: Props) => {
  return (
    <>
      {error && (
        <div className="bg-destructive/20 p-2 border-l-8 border-destructive">
          <p className="text-destructive">
            {error ? error : "Opss ocurrio un error"}
          </p>
        </div>
      )}
    </>
  );
};

export default ErrorMessage;
