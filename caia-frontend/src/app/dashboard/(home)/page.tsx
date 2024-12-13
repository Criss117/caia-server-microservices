"use client";
import { Button } from "@/core/shared/components/ui/button";
import { signOut, useSession } from "next-auth/react";

const DashBoardHome = () => {
  const { data: session, status } = useSession();

  return (
    <div>
      DashBoardHome
      <form
        action={async () => {
          await signOut();
        }}
      >
        <Button type="submit">Logout</Button>
      </form>
      <pre>
        <code>{JSON.stringify(session, null, 2)}</code>
      </pre>
      <pre>
        <code>{JSON.stringify(status, null, 2)}</code>
      </pre>
    </div>
  );
};

export default DashBoardHome;
